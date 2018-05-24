package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.controller.HosptailLogController;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.repository.SmHosptailLogRepository;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import com.jhmk.earlywaring.util.ObjectUtils;
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

import java.util.*;

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

    /**
     * 获取科室对应id
     *
     * @return
     */
    public Map<String, String> mapDept() {
        Iterable<SmDepts> all = smDeptRepService.findAll();
        Iterator<SmDepts> iterator = all.iterator();
        Map<String, String> param = new HashMap<>();
        while (iterator.hasNext()) {
            SmDepts dept = iterator.next();
            param.put(dept.getDeptCode(), dept.getDeptName().trim());
        }
        return param;
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

    public AtResponse getKpi() {
        AtResponse resp = new AtResponse();
        HttpHeaders headers = HttpHeadersUtils.getHeader();
        Map<String, String> postParameters = new HashMap<>();
//        if (deptName == null || deptName.equals("")) {
//            deptName = "全科室";
//        }
        postParameters.put("department", "全科室");
        postParameters.put("hospitalname", "测试医院");
        postParameters.put("flag", "patient");
        HttpEntity<String> r = new HttpEntity(postParameters, headers);
        String json = null;
        //发送
        try {

            json = restTemplate.postForObject(KPICOUNT, r, String.class);
            resp.setResponseCode(ResponseCode.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setResponseCode(ResponseCode.INERERROR);
            logger.info("服务器发生错误！" + e.getMessage());
        }
        resp.setData(json);
        return resp;
    }

    /**
     * 临床预警 数据统计 全院数据科室分析之科室门诊数和住院数
     *
     * @param startTime
     * @param endTime
     * @param deptName  父dept Name
     * @return
     */
    public String countForDate(String startTime, String endTime, String deptName) {
        HttpHeaders headers = HttpHeadersUtils.getHeader();
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("department", deptName);
        postParameters.put("startdate", startTime);
        postParameters.put("enddate", endTime);
        postParameters.put("flag", "patient");
        HttpEntity<String> r = new HttpEntity(postParameters, headers);
        //发送
        String json = restTemplate.postForObject(HOSPITALINFO, r, String.class);
        return json;

    }

    public SmHosptailLog addLog(String map) {
        SmHosptailLog smHosptailLog = new SmHosptailLog();
        Map<String, Object> jsonObject = (Map) JSON.parseObject(map);
        Object doctor_id = jsonObject.get("doctor_id");
        smHosptailLog.setAffirmSickness(ObjectUtils.flagObj(doctor_id));
        Object dept_id = jsonObject.get("dept_id");
        smHosptailLog.setDeptId(ObjectUtils.flagObj(dept_id));
        //病人id
        Object patient_id = jsonObject.get("patient_id");
        smHosptailLog.setSuffererId(ObjectUtils.flagObj(patient_id));
        Object shouyezhenduan = jsonObject.get("shouyezhenduan");
        //判断首页诊断如果不为空，确认主疾病为首页诊断
        if (shouyezhenduan != null) {
            Object diagnosis_name = ((Map) shouyezhenduan).get("diagnosis_name");
            if (!"".equals(diagnosis_name)) {
                smHosptailLog.setAffirmSickness(ObjectUtils.flagObj(diagnosis_name));
            }
        } else {
            Object binglizhenduan = jsonObject.get("binglizhenduan");
            Object diagnosis_name = ((Map) binglizhenduan).get("diagnosis_name");
            if ("".equals(diagnosis_name) || diagnosis_name == null) {
                logger.debug("确认主疾病存在问题！" + map.toString());
            }
            smHosptailLog.setAffirmSickness(ObjectUtils.flagObj(diagnosis_name));
        }
        smHosptailLog.setCreateTime(new Date());
        return smHosptailLog;

    }


}

