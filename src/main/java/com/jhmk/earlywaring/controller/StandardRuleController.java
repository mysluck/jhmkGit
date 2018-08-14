package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.entity.repository.service.SmShowLogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.entity.rule.FormatRule;
import com.jhmk.earlywaring.entity.rule.StandardRule;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.HosptailLogService;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.service.StandardRuleService;
import com.jhmk.earlywaring.service.UserModelService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.MapUtil;
import com.jhmk.earlywaring.webservice.AnalysisXmlService;
import com.jhmk.earlywaring.webservice.CdrService;
import jdk.nashorn.internal.scripts.JS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author ziyu.zhou
 * @date 2018/8/8 19:29
 */

@Controller
@RequestMapping("warn/standard")
public class StandardRuleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(StandardRuleController.class);


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RuleService ruleService;
    @Autowired
    SmShowLogRepService smShowLogRepService;
    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    HosptailLogService hosptailLogService;

    @Autowired
    UserModelService userModelService;
    @Autowired
    UrlConfig urlConfig;
//    @Autowired
//    OriRuleRepService oriRuleRepService;
    @Autowired
    CdrService cdrService;
    @Autowired
    StandardRuleService standardRuleService;
    @Autowired
    AnalysisXmlService analysisXmlService;


    /**
     * 获取所有标准规则
     */
    @PostMapping("/getAllStandardRule")
    public void getAllStandardRule(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        AtResponse resp = new AtResponse();
        String result = "";
        Map<String, Object> dataMap = null;
        try {

            result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.findallstandardrules, parse, String.class);
            dataMap = ruleService.formatData(result);
        } catch (Exception e) {
            logger.info("查询所有标准规则信息失败:{},错误信息为:{}", result, e.getMessage());

        } finally {

        }
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(dataMap);
        wirte(response, resp);
    }


    /**
     * 根据id获取标准规则
     *
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/getStandardRuleById")
    @ResponseBody
    public void getStandardRuleById(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {
        AtResponse resp = new AtResponse();

        Object o = JSONObject.parse(map);
        String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.getruleforid, o, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Map obj = (Map) JSONObject.parse(jsonObject.get("result").toString());
        //获取cdss规则
        FormatRule formatRule = MapUtil.map2Bean(obj, FormatRule.class);
        //解析 标准规则
        List<List<StandardRule>> basicStandardRule = standardRuleService.getBasicStandardRule(formatRule.getChildElement());
        resp.setData(basicStandardRule);
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);
    }

    @PostMapping("/delChildRuleByCondition")
    @ResponseBody
    public void delChildRuleByCondition(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {

        JSONObject jsonObject = JSONObject.parseObject(map);
        //标准规则id
        String id = jsonObject.getString("id");
        String field = jsonObject.getString("field");

        String standardName = jsonObject.getString("standardName");
        //新加字段
        //查询规则实体
        FormatRule standRule = ruleService.getFormatRuleById(id);
        List<FormatRule> allChildRule = standardRuleService.findAllChildRule(id);
        for (FormatRule formatRule : allChildRule) {
            standardRuleService.deleteChildRuleByCondition(field, formatRule);
        }
        standardRuleService.updataStandardChildElement(field, standardName, standRule, false);
        AtResponse atResponse = new AtResponse();
        atResponse.setResponseCode(ResponseCode.OK);
        wirte(response, atResponse);
    }

    /**
     * 1 获取添加的标准规则名
     * 2.拼接规则信息 成List<String>
     * 3.解析标准规则 获取标准规则的提示 释义 状态等
     * 4.添加规则入库
     */
    @PostMapping("/addChildRuleByCondition")
    @ResponseBody
    public void addChildRuleByCondition(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {
        AtResponse resp = new AtResponse();
        //获取要删除的条件字段
        //标准规则id
        JSONObject jsonObject = JSONObject.parseObject(map);
        String id = jsonObject.getString("id");
        String field = jsonObject.getString("field");


        String standardName = jsonObject.getString("standardName");
        //新加字段
        //查询规则实体
        FormatRule formatRule = ruleService.getFormatRuleById(id);
        formatRule.setDoctorId(getUserId());
        String childElement = formatRule.getChildElement();
        boolean b = standardRuleService.updataStandardChildElement(field, standardName, formatRule, true);
        if (b) {
            List<List<StandardRule>> basicStandardRuleList = standardRuleService.getBasicStandardRule(childElement);
            // 删除 此标准规则下所有字段 并替换新加字段  然后互相交叉添加子规则
            for (int i = 0, x = basicStandardRuleList.size(); i < x; i++) {
                List<StandardRule> standardRuleList = basicStandardRuleList.get(i);
                for (int j = 0, y = basicStandardRuleList.size(); j < y; j++) {
                    StandardRule standardRule = standardRuleList.get(j);
                    if (standardName.equals(standardRule.getStandardValue())) {
                        basicStandardRuleList.get(i).get(j).setAllValues(Arrays.asList(field.split(",")));
                    }
                }

            }
            //获取要添加的子规则条件
            List<String> list = standardRuleService.addCdssRule(basicStandardRuleList, new LinkedList<>());
            for (String s : list) {
                if (!ruleService.isRule(s)) {
                    s = ruleService.updateOldRule(s);
                    ruleService.addChildRuleByCondition(s, formatRule);
                }
            }
            //修改标准规则的子规则条件字段
        } else {
            resp.setResponseCode(ResponseCode.UPDATERULEERROR1);
            wirte(response, resp);
        }

    }


}
