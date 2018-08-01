package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.entity.HospitalLog;
import com.jhmk.earlywaring.entity.LogBean;
import com.jhmk.earlywaring.entity.SmHospitalLog;
import com.jhmk.earlywaring.entity.repository.service.*;
import com.jhmk.earlywaring.entity.rule.LogMapping;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.HosptailLogService;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/warn/smHosptailLog")
public class HosptailLogController extends BaseEntityController<SmHospitalLog> {


    private static final Logger logger = LoggerFactory.getLogger(HosptailLogController.class);

    @Autowired
    HosptailLogService hosptailLogService;
    @Autowired
    SmHospitalLogRepService smHospitalLogRepService;
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
    @Autowired
    LogMappingRepService logMappingRepService;
    @Autowired
    UrlConfig urlConfig;


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
        List<LogBean> hospitalMap = hosptailLogService.countForField(startTime, endTime, deptName, BaseConstants.ALARM_CODE1);
        List<LogBean> clinicMap = hosptailLogService.countForField(startTime, endTime, deptName, BaseConstants.ALARM_CODE2);

        Map<String, List> resultMap = new HashMap<>();
        resultMap.put("hospital", hospitalMap);
        resultMap.put("clinic", clinicMap);
        wirte(response, resultMap);
    }


    /**
     * 触发预警统计
     *
     * @param response
     */
    @PostMapping("/getAlartCount")
    @ResponseBody
    public void getAlartCount(HttpServletResponse response) {
        AtResponse<Map<String, Long>> resp = new AtResponse();
        long dept = smHospitalLogRepService.countByWarnSource(BaseConstants.ALARM_CODE1);
        long mz = smHospitalLogRepService.countByWarnSource(BaseConstants.ALARM_CODE2);
        //总预警
        long allCount = dept + mz;
        Map<String, Long> data = new HashMap<>();
        data.put("deptCount", dept);
        data.put("hospitalCount", mz);
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
        AtResponse<List<LogBean>> resp = new AtResponse<>();
        Map<String, String> paramMap = (Map) JSON.parse(map);
        int yearNow;
        if (paramMap != null) {
            yearNow = Integer.valueOf(paramMap.get("year"));
        } else {
            yearNow = DateFormatUtil.getYearNow();
        }

        List<LogBean> logBeans = hosptailLogService.countByDept(yearNow);
        resp.setData(logBeans);
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
        AtResponse resp = new AtResponse<>();
        List<String> list = hosptailLogService.mapDeptNames();
        resp.setData(list);
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);

    }

    /**
     * 获取科室数量
     */
    @PostMapping("/getDepts")
    @ResponseBody
    public void getDepts(HttpServletResponse response) {
        AtResponse resp = new AtResponse<>();
        //获取总科室（心内科 ）
//        List<DeptRel> distinctByDeptCode = deptRelRepService.findDistinctByDeptCode();
        // 获取单科室 如心内科1 心内科2 (日志表中有数量的)
        List<String> distinctByDeptCode = smHospitalLogRepService.getCountByDistinctDeptCode();
        resp.setData(distinctByDeptCode);
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
        List<SmHospitalLog> countByDistinctDoctorId = smHospitalLogRepService.getCountByDistinctDoctorId();
        atResponse.setData(countByDistinctDoctorId);
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
        AtResponse atResponse = new AtResponse(System.currentTimeMillis());
        String deptName;
        int yearTime = 0;
        Map<String, String> paramMap = (Map) JSON.parse(map);
        if (paramMap != null) {
            yearTime = Integer.valueOf(paramMap.get("year"));
            deptName = paramMap.get("deptId");
        } else {
            yearTime = DateFormatUtil.getYearNow();
            deptName = getDeptName();
        }
        //人员分布
        List<LogBean> logBeans = hosptailLogService.countByDoctor(deptName, yearTime);
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setMessage(BaseConstants.SUCCESS);
        atResponse.setData(logBeans);
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
            deptId = getDeptName();
        }
        //触发疾病统功能
        Map<String, Integer> countByDeptAndSickness = hosptailLogService.getCountByDeptAndSickness(deptId, yearTime);
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setData(countByDeptAndSickness);
        wirte(response, atResponse);
    }

    /**
     * 根据条件查询触发的规则详细信息（pid、vid、规则id）
     *
     * @param response
     * @param map
     */
    @PostMapping(value = "/getDataByCondition")
    @ResponseBody
    public void getDataByCondition(HttpServletResponse response, @RequestBody(required = false) String map) {
        AtResponse atsp = new AtResponse();
        Map<String, String> params = (Map) JSONObject.parse(map);
        Map<String, String> condMap = new HashMap<>();
        Date yearFirst = null;
        Date yearLast = null;
        if (StringUtils.isNotBlank(params.get("year"))) {
            Integer year = Integer.valueOf(params.get("year"));
            yearFirst = DateFormatUtil.getYearFirst(year);
            yearLast = DateFormatUtil.getYearLast(year);
        }
        if (StringUtils.isNotBlank(params.get("deptCode"))) {
            condMap.put("deptCode", params.get("deptCode"));
        }
        if (StringUtils.isNotBlank(params.get("doctorId"))) {
            condMap.put("doctorId", params.get("doctorId"));
        }
        if (StringUtils.isNotBlank(params.get("doctorName"))) {
            condMap.put("doctorName", params.get("doctorName"));
        }
        if (StringUtils.isNotBlank(params.get("alarmLevel"))) {
            condMap.put("alarmLevel", params.get("alarmLevel"));
        }
        Integer pageNo = 0;
        if (StringUtils.isNotBlank(params.get("pageNo"))) {
            pageNo = Integer.valueOf(params.get("pageNo"));
        }
        List<SmHospitalLog> dataByCondition = hosptailLogService.getDataByConditionBySort(yearFirst, yearLast, condMap, pageNo, null);
        List<SmHospitalLog>beanList =new LinkedList<>();
        for (SmHospitalLog log:dataByCondition) {
            List<LogMapping> allByLogId = logMappingRepService.findAllByLogId(log.getId());
            String ruleCondition = log.getRuleCondition();
            for (LogMapping mapping:allByLogId){
                if (ruleCondition.contains(mapping.getLogResult())){
                    String logTime = mapping.getLogTime();
                    String value=mapping.getLogResult();
                    if (StringUtils.isNotBlank(logTime)){
                        value=mapping.getLogResult()+"("+logTime+")";
                    }
                    ruleCondition = ruleCondition.replaceAll(mapping.getLogResult(), value);
                }
            }
            log.setRuleCondition(ruleCondition);
            beanList.add(log);
        }
        atsp.setResponseCode(ResponseCode.OK);
        atsp.setData(dataByCondition);
        wirte(response, atsp);
    }

    /**
     *
     */
    @PostMapping(value = "/getLogFile")
    public void getLogFile(HttpServletResponse response) {
        String fileName = "/data/1/CDSS/demo.txt";
//        String fileName = "C:/嘉和美康文档/demo.txt";
        File file = new File(fileName);
        BufferedWriter br = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            br = new BufferedWriter(new FileWriter(file));
            Iterable<SmHospitalLog> all = smHospitalLogRepService.findAll();
            Iterator<SmHospitalLog> iterator = all.iterator();
            while (iterator.hasNext()) {
                SmHospitalLog next = iterator.next();
                StringBuffer sb = new StringBuffer();
                sb.append(next.getId()).append(",")
                        .append(next.getDoctorId()).append(",")
                        .append(next.getDoctorName()).append(",")
                        .append(next.getPatientId()).append(",")
                        .append(next.getVisitId()).append(",")
                        .append(next.getDeptCode()).append(",")
                        .append(next.getRuleId()).append(",")
                        .append(next.getDiagnosisName()).append(",")
                        .append(next.getHintContent()).append(",")
                        .append(next.getRuleSource()).append(",")
                        .append(next.getSignContent()).append(",")
                        .append(next.getCreateTime());
                br.write(sb.toString());
                br.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
