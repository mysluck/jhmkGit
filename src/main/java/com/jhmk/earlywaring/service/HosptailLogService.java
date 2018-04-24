package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.controller.HosptailLogController;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.repository.SmHosptailLogRepository;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HosptailLogService extends BaseRepService<SmHosptailLog, Integer> {

    Logger logger = Logger.getLogger(SmHosptailLog.class);

    @Autowired
    SmHosptailLogRepService smHosptailLogRepService;
    @Autowired
    SmDeptsRepService smDeptRepService;

    @Autowired
    RestTemplate restTemplate;

    private static final String KPICOUNT = "http://192.168.8.20:8820/demo/med/cindecision/patinetmednum.json";
    private static final String HOSPITALINFO = "http://192.168.8.20:8820/demo/med/cindecision/gethospizationinfo.json";
    private static final String DEPTCOUNT = "http://192.168.8.20:8820/demo/med/cindecision/medicalRecordsOfDepartment.json";


    private static void addToMap(HashMap map, Class[] array) {


        for (int i = 0; i < array.length; i++) {
            String name = array[i].getName();
            map.put(name.substring(name.lastIndexOf(".") + 1, name.length()), array[i]);
        }
    }


    public Map<String, Integer> getMap(String deptId, int year) {
        Date startTime = DateFormatUtil.getYearFirst(year);
        Date endTime = DateFormatUtil.getYearLast(year);
        Map<String, Integer> map = new HashMap<>();
        //根据部门查询所有log
        List<SmHosptailLog> hosptailLogList = smHosptailLogRepService.getAllByDeptAndYear(deptId, BaseConstants.ALARMSTSTUS1, startTime, endTime);
        if (hosptailLogList.size() > 0) {
            for (SmHosptailLog log : hosptailLogList) {
                //将log 的key：value  主疾病 ：次数
                if (map.containsKey(log.getAffirmSickness())) {
                    map.put(log.getAffirmSickness(), map.get(log.getAffirmSickness()) + 1);
                } else {
                    map.put(log.getAffirmSickness(), 1);
                }
            }
        }
        return map;

    }

    /**
     * 触发疾病统计
     *
     * @param deptId
     * @param year
     * @return
     */
    public Map<String, Integer> getCountByDeptAndSickness(String deptId, int year) {
        Date startTime = DateFormatUtil.getYearFirst(year);
        Date endTime = DateFormatUtil.getYearLast(year);
        //用于分页  相当于limit（0,10）
        Pageable pageable = new PageRequest(0, 10);
        List<Object[]> list = smHosptailLogRepService.getCountByAffirmSicknessAndDeptId(deptId, BaseConstants.ALARMSTSTUS1, startTime, endTime, pageable);
        //因为list中查出来的数据是有序的  所以用linkedhashmap存储，有序

        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = list.get(i);
            map.put(objects[0].toString(), Integer.valueOf(objects[1].toString()));
        }
        return map;

    }


    //科室分布功能
    public Map<String, Map<String, Integer>> countByDept(int year) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        List<SmHosptailLog> logList = smHosptailLogRepService.getDeptCountByYear(BaseConstants.ALARMSTSTUS1, DateFormatUtil.getYearFirst(year), DateFormatUtil.getYearLast(year));
        for (SmHosptailLog smHosptailLog : logList) {
            if (map.containsKey(smHosptailLog.getDeptId())) {
                Map<String, Integer> childMap = map.get(smHosptailLog.getDeptId());
                if (childMap.containsKey(smHosptailLog.getSicknessGrade())) {
                    childMap.put(smHosptailLog.getSicknessGrade(), childMap.get(smHosptailLog.getSicknessGrade()) + 1);
                } else {
                    childMap.put(smHosptailLog.getSicknessGrade(), 1);
                }
                map.put(smHosptailLog.getFaDeptName(), childMap);
            } else {
                Map<String, Integer> childMap = new HashMap<>();
                childMap.put(smHosptailLog.getSicknessGrade(), 1);
                map.put(smHosptailLog.getFaDeptName(), childMap);
            }
        }
        return map;
    }


    //人员分布功能
    public Map<String, Map<String, Integer>> countByDeptAndUser(String deptId, int year) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        List<SmHosptailLog> logList = smHosptailLogRepService.getAllByDeptAndYear(deptId, BaseConstants.ALARMSTSTUS1, DateFormatUtil.getYearFirst(year), DateFormatUtil.getYearLast(year));
        for (SmHosptailLog smHosptailLog : logList) {
            if (map.containsKey(smHosptailLog.getDoctorId())) {
                Map<String, Integer> childMap = map.get(smHosptailLog.getDoctorId());
                if (childMap.containsKey(smHosptailLog.getSicknessGrade())) {
                    childMap.put(smHosptailLog.getSicknessGrade(), childMap.get(smHosptailLog.getSicknessGrade()) + 1);
                } else {
                    childMap.put(smHosptailLog.getSicknessGrade(), 1);
                }
                map.put(smHosptailLog.getDoctorId(), childMap);
            } else {
                Map<String, Integer> childMap = new HashMap<>();
                childMap.put(smHosptailLog.getSicknessGrade(), 1);
                map.put(smHosptailLog.getDoctorId(), childMap);
            }
        }
        return map;
    }

    /**
     * 根据部门名称  获取kpi统计
     *
     * @return
     */

    public Map<String, Object> getKpi() {
        HttpHeaders headers = getHeader();
        Map<String, String> postParameters = new HashMap<>();
//        if (deptName == null || deptName.equals("")) {
//            deptName = "全科室";
//        }
        postParameters.put("department", "全科室");
        postParameters.put("hospitalname", "测试医院");
        postParameters.put("flag", "patient");
        HttpEntity<String> r = new HttpEntity(postParameters, headers);
        //发送
        String json = restTemplate.postForObject(KPICOUNT, r, String.class);

        JSONObject jsonObject = JSONObject.parseObject(json);
        String code = (String) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        Map<String, Object> result = null;
//        if (BaseConstants.OK.equals(code) && BaseConstants.SUCCESS.equals(message)) {
        result = (Map) jsonObject.get("result");
        return result;
//        }
//        logger.info("统计kpi科室部门无法匹配：:"+deptName);
//        return result;
    }

    /**
     * 临床预警 数据统计 全院数据科室分析之科室门诊数和住院数
     *
     * @param startTime
     * @param endTime
     * @param deptName  父dept Name
     * @return
     */
    public Map<String, Object> countForDate(String startTime, String endTime, String deptName) {
        HttpHeaders headers = getHeader();
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("department", deptName);
        postParameters.put("startdate", startTime);
        postParameters.put("enddate", endTime);
        postParameters.put("flag", "patient");
        HttpEntity<String> r = new HttpEntity(postParameters, headers);
        //发送
        String json = restTemplate.postForObject(HOSPITALINFO, r, String.class);

        JSONObject jsonObject = JSONObject.parseObject(json);
        String code = (String) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (BaseConstants.OK.equals(code) && BaseConstants.SUCCESS.equals(message)) {
            Map<String, Object> result = (Map) jsonObject.get("result");
            if(result!=null){
                Map<String, Object> o = (Map)result.get(deptName);
            return o;
            }
            logger.info(HOSPITALINFO+"查询失败：部门id" + code + ",返回信息：" + message);

            return null;
        } else {
            logger.error("全院数据科室分析之科室门诊数和住院数查询失败：部门id" + code + ",返回信息：" + message);
            return null;
        }

    }


    private HttpHeaders getHeader() {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        return headers;
    }

}

