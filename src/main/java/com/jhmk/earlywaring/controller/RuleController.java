package com.jhmk.earlywaring.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    SmHosptailLogRepService smHosptailLogRepService;

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
        Map<String, String> params = new HashMap<>();
        Map<String, String> param = (Map) JSONObject.parse(map);
        params.putAll(param);
        String userId = getUserId();
        params.put("doctorId", userId);
        String time = DateFormatUtil.format(new Timestamp(System.currentTimeMillis()), DateFormatUtil.DATETIME_PATTERN_SS);
        params.put("createTime", time);
        System.out.println(userId);
        Object o = JSON.toJSON(params);
        String forObject = restTemplate.postForObject(BaseConstants.addrule, o, String.class);
        wirte(response, forObject);
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
        String forObject = restTemplate.postForObject(BaseConstants.findallnotsubmitrule, o, String.class);
        wirte(response, forObject);
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
        Object o = ruleService.ruleMatch(map);

        //todo规则匹配 暂无接口
        String forObject = restTemplate.postForObject(BaseConstants.submitrule, o, String.class);
        wirte(response, forObject);
    }



}
