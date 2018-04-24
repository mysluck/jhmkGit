package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmEvaluate;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.repository.service.DeptRelRepService;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.HosptailLogService;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;

@Controller
@RequestMapping("/smHosptailLog")
public class HosptailLogController extends BaseEntityController<SmHosptailLog> {


    @Autowired
    HosptailLogService hosptailLogService;
    @Autowired
    SmHosptailLogRepService smHosptailLogRepService;
    @Autowired
    SmDeptsRepService smDeptRepService;
    @Autowired
    RuleService ruleService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DeptRelRepService deptRelRepService;



    /**
     * 获取医院kpi数据
     *
     * @param response
     */
    @PostMapping("/getKpi")
    @ResponseBody
    public void getKpi(HttpServletResponse response) {
        Map<String, Object> data = hosptailLogService.getKpi();
        wirte(response, data);
    }

    /**
     * 门诊人数+住院人数统计
     *
     * @param response
     * @param map
     */
    @PostMapping("/getCountByDate")
    @ResponseBody
    public void getCountByDate(HttpServletResponse response, @RequestBody String map) {
        Map<String, String> paramMap = (Map) JSON.parse(map);
        String startTime = paramMap.get("startTime");
        String endTime = paramMap.get("endTime");
        String deptName = paramMap.get("deptName");
        Map<String, Object> data = hosptailLogService.countForDate(startTime, endTime, deptName);
        wirte(response, data);
    }

    /**
     * 触发预警统计
     *
     * @param response
     */
    @PostMapping("/getAlartCount")
    @ResponseBody
    public void getAlartCount(HttpServletResponse response) {
        //科室预警c次数
        int deptCount = smHosptailLogRepService.getCountAllByAlarmCode(BaseConstants.ALARM_CODE1, BaseConstants.ALARMSTSTUS1);
        //住院预警
        int hospitalCount = smHosptailLogRepService.getCountAllByAlarmCode(BaseConstants.ALARM_CODE2, BaseConstants.ALARMSTSTUS1);
        //总预警
        int allCount = deptCount + hospitalCount;
        Map<String, Object> data = new HashMap<>();
        data.put("deptCount", deptCount);
        data.put("hospitalCount", hospitalCount);
        data.put("allCount", allCount);
        wirte(response, data);
    }

    /**
     * 触发预警科室分布统计
     *
     * @param response
     * @param map      年份
     */
    @PostMapping("/getDeptCount")
    @ResponseBody
    public void getDeptCount(HttpServletResponse response, @RequestBody(required = false) String map) {
        Map<String, String> paramMap = (Map) JSON.parse(map);
        int yearNow;
        if (paramMap != null) {
            yearNow = Integer.valueOf(paramMap.get("year"));
        } else {
            yearNow = DateFormatUtil.getYearNow();
        }

        Map<String, Map<String, Integer>> deptForSicknesss = hosptailLogService.countByDept(yearNow);
        wirte(response, deptForSicknesss);
    }

    /**
     * 获取科室数量
     */
    @PostMapping("/getDepts")
    @ResponseBody
    public void getDepts(HttpServletResponse response) {
        List<DeptRel> distinctByDeptCode = deptRelRepService.findDistinctByDeptCode();
        wirte(response, distinctByDeptCode.size());
    }


    /**
     * 获取主管医生数量 todo
     *
     */
    @PostMapping("/getDoctors")
    @ResponseBody
    public void getDoctors(HttpServletResponse response) {
        List<DeptRel> distinctByDeptCode = deptRelRepService.findDistinctByDeptCode();
        wirte(response, distinctByDeptCode.size());
    }



    /**
     * 触发预警人员分布统计
     *
     * @param response
     * @param map
     */
    @PostMapping("/getPersonCount")
    @ResponseBody
    public void getPersonCount(HttpServletResponse response, @RequestBody(required = false) String map) {
        String deptId;
        int yearTime = 0;
        Map<String, String> paramMap = (Map) JSON.parse(map);
        if (paramMap != null) {
            yearTime = Integer.valueOf(paramMap.get("year"));
            deptId = paramMap.get("deptId");
        } else {
            yearTime = DateFormatUtil.getYearNow();
            deptId = getFuDeptId();
        }

        Map<String, Object> data = new HashMap<>();
        //人员分布
        Map<String, Map<String, Integer>> peopleForSicknesss = hosptailLogService.countByDeptAndUser(deptId, yearTime);
        data.put("peopleForSicknesss", peopleForSicknesss);
        wirte(response, data);
    }


    /**
     * 触发疾病统计
     * todo
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCountForSick")
    @ResponseBody
    public void getCountForSick(HttpServletResponse response, @RequestBody(required = false) String map) {


        String deptId;
        int yearTime = 0;
        Map<String, String> paramMap = (Map) JSON.parse(map);
        if (paramMap != null) {
            yearTime = Integer.valueOf(paramMap.get("year"));
            deptId = paramMap.get("deptId");
        } else {
            yearTime = DateFormatUtil.getYearNow();
            deptId = getFuDeptId();
        }
        //触发疾病统功能
        Map<String, Integer> countByDeptAndSickness = hosptailLogService.getCountByDeptAndSickness(deptId, yearTime);
        wirte(response, countByDeptAndSickness);
    }

}
