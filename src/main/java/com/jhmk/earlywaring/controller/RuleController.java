package com.jhmk.earlywaring.controller;


import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    //添加操作
    @RequestMapping(value = "/getUnSubmitRule")
    public AtResponse addUser() {
        AtResponse<Map<String, Object>> resp = new AtResponse<>(System.currentTimeMillis());
        Map<String, Object> data = new HashMap<>();
        String userId = getUserId();
        List<Map<String, Object>> unSubmitRule = ruleService.getUnSubmitRule(userId);
        return resp;
    }

    //添加操作
    @RequestMapping(value = "/add")
    @ResponseBody
    public AtResponse addUser(String rule) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());

        String save = null;
        if (save == null) {
            resp.setMessage("添加失败,请重新添加！");
            resp.setResponseCode(ResponseCode.INERERROR);
            return resp;
        } else {
            resp.setMessage("添加成功！");
            resp.setResponseCode(ResponseCode.OK);
            return resp;
        }

    }

    //删除
    @RequestMapping(value = "/delete")
    @ResponseBody
    public AtResponse<String> delete(@RequestParam(name = "id", required = true) String id) {
//        ruleRepService.delete(Integer.valueOf(id));
        String message;
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        message = "删除成功";
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(message);
        return resp;
    }


    //未提交规则查看 todo
    @RequestMapping(value = "/listNotSubmitRule")
    @ResponseBody
    public AtResponse<String> listNotSubmitRule() {
//        ruleRepService.findAll();
        String message;
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        message = "删除成功";
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(message);
        return resp;
    }


    //已提交规则查看 todo
    @RequestMapping(value = "/listSubmitRule")
    @ResponseBody
    public AtResponse<String> listSubmitRule() {
//        ruleRepService.findAll();
        String message;
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        message = "删除成功";
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(message);
        return resp;
    }


    //提交操作 todo
    @RequestMapping(value = "/submitRule")
    @ResponseBody
    public AtResponse submit(@ModelAttribute String rule) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());

//        Rule save = ruleRepService.save(rule);
        String save = null;
        if (save == null) {
            resp.setMessage("添加失败,请重新添加！");
            resp.setResponseCode(ResponseCode.INERERROR);
            return resp;
        } else {
            resp.setMessage("添加成功！");
            resp.setResponseCode(ResponseCode.OK);
            return resp;
        }

    }

    /**
     * 查询规则
     *
     * @param json
     * @return
     */
    @RequestMapping("/preRule")
    public String reciveRule(String json) {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        SmHosptailLog data = null;
//        restTemplate.postForObject(URL, json, SmHosptailLog.class);
        if (BaseConstants.ALARMSTSTUS1.equals(data.getAlarmStatus())) {
            smHosptailLogRepService.save(data);
            //返回警报提示信息
            String alarmResult = data.getAlarmResult();
            return alarmResult;
        } else {
            return "";
        }
    }
}
