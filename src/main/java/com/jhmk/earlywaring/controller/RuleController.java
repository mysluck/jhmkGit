package com.jhmk.earlywaring.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.entity.rule.FormatRule;
import com.jhmk.earlywaring.entity.rule.ReciveRule;
import com.jhmk.earlywaring.entity.rule.SendRule;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.HosptailLogService;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.service.UserModelService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.*;

/**
 * 规则管理
 */
@Controller
@RequestMapping("/rule")
public class RuleController extends BaseEntityController<Object> {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RuleService ruleService;
    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    HosptailLogService hosptailLogService;

    @Autowired
    UserModelService userModelService;
    @Autowired
    SmHosptailLogRepService smHosptailLogRepService;

    private static final Logger logger = LogManager.getLogger(RuleController.class);

    /**
     * 获取变量列表
     *
     * @param response
     */
    @GetMapping("/getVariableList")
    @ResponseBody
    public void getVariableList(HttpServletResponse response) {
        String forObject = restTemplate.getForObject(BaseConstants.getVariableList, String.class);
        wirte(response, forObject);
    }

    /**
     * 添加规则
     *
     * @param response
     */
    @PostMapping("/addrule")
    @ResponseBody
    public void addrule(HttpServletResponse response, @RequestBody String map) {
        //获取原始规则条件
        Map<String, Object> param = (Map) JSONObject.parse(map);
        Object condition = param.get("ruleCondition");
        //转换条件格式
        String ruleCondition = userModelService.analyzeOldRule(condition);
        param.put("ruleCondition", ruleCondition);

        String userId = getUserId();
        param.put("doctorId", userId);
        String time = DateFormatUtil.format(new Timestamp(System.currentTimeMillis()), DateFormatUtil.DATE_PATTERN_S);
        param.put("createTime", time);
        Object o = JSON.toJSON(param);
        String forObject = "";
        try {
            forObject = restTemplate.postForObject(BaseConstants.addrule, o, String.class);
        } catch (Exception e) {
            logger.info("添加规则失败：" + o);
        } finally {
            wirte(response, forObject);
        }
    }

    /**
     * 6.查询所有未提交的规则信息
     *
     * @param response
     */
    @PostMapping("/findallnotsubmitrule")
    @ResponseBody
    public void findallnotsubmitrule(HttpServletResponse response) {
        String userId = getUserId();
        Map<String, String> params = new HashMap<>();
        params.put("doctorId", userId);
        Object o = JSONObject.toJSON(params);
        String forObject = "";
        List<FormatRule> foramtData = null;
        try {
            forObject = restTemplate.postForObject(BaseConstants.findallnotsubmitrule, o, String.class);
            foramtData = ruleService.formatData(forObject);


        } catch (Exception e) {
            logger.info("查询所有未提交的规则信息失败：" + o);
        } finally {
            wirte(response, foramtData);
        }
    }

    /**
     * 6. 查询所有提交的规则信息
     *
     * @param response
     */
    @PostMapping("/findallsubmitrule")
    @ResponseBody
    public void findallsubmitrule(HttpServletResponse response) {
        String userId = getUserId();
        Map<String, String> params = new HashMap<>();
        params.put("doctorId", userId);
        Object o = JSONObject.toJSON(params);
        String forObject = restTemplate.postForObject(BaseConstants.findallsubmitrule, o, String.class);
        wirte(response, forObject);
    }


    /**
     * 6. 提交规则
     *
     * @param response
     */
    @PostMapping("/submitrule")
    @ResponseBody
    public void submitrule(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        String forObject = restTemplate.postForObject(BaseConstants.submitrule, parse, String.class);
        wirte(response, forObject);
    }

    /**
     * 6. 规则匹配
     *
     * @param response
     */
    @PostMapping("/ruleMatch")
    @ResponseBody
    public void ruleMatch(HttpServletResponse response, @RequestBody String map) {
        ReciveRule sendRule = ruleService.ruleMatch(map);
        Object o = JSON.toJSON(sendRule);
        System.out.println(o.toString());
        AtResponse resp = new AtResponse();
        String data = "";
        try {

            data = restTemplate.postForObject(BaseConstants.matchrule, o, String.class);
            Map<String, Object> result = (Map<String, Object>) JSON.parse(data);
            if ("200".equals(result.get("code"))) {
                if (result.get("result") != null) {

                    resp.setMessage(BaseConstants.SUCCESS);
                    resp.setData(result.get("result"));
                    Object result1 = result.get("result");
                    hosptailLogService.addLog(map);

                } else {
                    logger.info(map + "没有匹配到规则！" + data);
                    resp.setMessage(BaseConstants.FALSE);
                }
                resp.setResponseCode(ResponseCode.OK);
            } else {
                logger.info("规则匹配失败！" + data);
                resp.setMessage(BaseConstants.FALSE);
                resp.setResponseCode(ResponseCode.INERERROR);
            }
        } catch (Exception e) {
            logger.info("cdss服务器规则匹配失败！" + e.getMessage());
            resp.setMessage("规则匹配失败！服务器发生异常");
            resp.setResponseCode(ResponseCode.INERERROR);
        } finally {
            wirte(response, resp);
        }

    }
}
