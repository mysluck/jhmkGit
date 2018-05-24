package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.repository.service.DeptRelRepService;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.HosptailLogService;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/warn/smHosptailLog")
public class HosptailLogController extends BaseEntityController<SmHosptailLog> {


    private static final Logger logger = LoggerFactory.getLogger(HosptailLogController.class);

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
    SmUsersRepService smUsersRepService;
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
        AtResponse data = hosptailLogService.getKpi();
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
        String startTime = paramMap.get("startdate");
        String endTime = paramMap.get("enddate");
        String deptName = paramMap.get("department");
        String data = hosptailLogService.countForDate(startTime, endTime, deptName);
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
        AtResponse<Map<String, Object>> resp = new AtResponse();
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
        resp.setData(data);
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);
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
        AtResponse<Map<String, Map<String, Integer>>> resp = new AtResponse<>();
        Map<String, String> paramMap = (Map) JSON.parse(map);
        int yearNow;
        if (paramMap != null) {
            yearNow = Integer.valueOf(paramMap.get("year"));
        } else {
            yearNow = DateFormatUtil.getYearNow();
        }

        Map<String, Map<String, Integer>> deptForSicknesss = hosptailLogService.countByDept(yearNow);
        resp.setData(deptForSicknesss);
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);
    }


    /**
     * 获取部门映射 deptcode ： deptName
     *
     * @param response
     */
    @PostMapping("/mapDept")
    @ResponseBody
    public void mapDept(HttpServletResponse response) {
        AtResponse<Map<String, String>> resp = new AtResponse<>();
        Map<String, String> map = hosptailLogService.mapDept();
        resp.setData(map);
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);

    }

    /**
     * 获取科室数量
     */
    @PostMapping("/getDepts")
    @ResponseBody
    public void getDepts(HttpServletResponse response) {
        AtResponse<Integer> resp = new AtResponse<>();
        //获取总科室（心内科 ）
//        List<DeptRel> distinctByDeptCode = deptRelRepService.findDistinctByDeptCode();
        // 获取单科室 如心内科1 心内科2 (日志表中有数量的)
        List<SmHosptailLog> distinctByDeptCode = smHosptailLogRepService.getCountByDistinctDeptId();
        resp.setData(distinctByDeptCode.size());
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);
    }


    /**
     * 获取主管医生数量
     */
    @PostMapping("/getDoctors")
    @ResponseBody
    public void getDoctors(HttpServletResponse response) {
        AtResponse atResponse = new AtResponse(System.currentTimeMillis());
        long size = smUsersRepService.count();
        atResponse.setData(size);
        atResponse.setResponseCode(ResponseCode.OK);
        wirte(response, atResponse);
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
        AtResponse<Map<String, Map<String, Integer>>> atResponse = new AtResponse(System.currentTimeMillis());
        String deptId;
        int yearTime = 0;
        Map<String, String> paramMap = (Map) JSON.parse(map);
        if (paramMap != null) {
            yearTime = Integer.valueOf(paramMap.get("year"));
            deptId = paramMap.get("deptId");
        } else {
            yearTime = DateFormatUtil.getYearNow();
            deptId = getDeptId();
        }
        //人员分布
        Map<String, Map<String, Integer>> peopleForSicknesss = hosptailLogService.countByDeptAndUser(deptId, yearTime);
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setMessage(BaseConstants.SUCCESS);
        atResponse.setData(peopleForSicknesss);
        wirte(response, atResponse);
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

        AtResponse<Map<String, Integer>> atResponse = new AtResponse(System.currentTimeMillis());
        String deptId;
        int yearTime = 0;
        Map<String, String> paramMap = (Map) JSON.parse(map);
        if (paramMap != null) {
            yearTime = Integer.valueOf(paramMap.get("year"));
            deptId = paramMap.get("deptId");
        } else {
            yearTime = DateFormatUtil.getYearNow();
            deptId = getDeptId();
        }
        //触发疾病统功能
        Map<String, Integer> countByDeptAndSickness = hosptailLogService.getCountByDeptAndSickness(deptId, yearTime);
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setData(countByDeptAndSickness);
        wirte(response, atResponse);
    }

}
