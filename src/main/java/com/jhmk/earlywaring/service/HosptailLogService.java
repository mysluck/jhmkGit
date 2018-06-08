package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.controller.HosptailLogController;
import com.jhmk.earlywaring.entity.LogBean;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.repository.SmHosptailLogRepository;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.model.WebPage;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import com.jhmk.earlywaring.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    @Autowired
    UrlConfig urlConfig;

    private static String blue = "蓝色预警";
    private static String red = "红色预警";
    private static String orange = "橙色预警";
    private static String yellow = "黄色预警";

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
        List<SmHosptailLog> hosptailLogList = smHosptailLogRepService.getAllByDeptAndYear(deptId, startTime, endTime);
        if (hosptailLogList.size() > 0) {
            for (SmHosptailLog log : hosptailLogList) {
                //将log 的key：value  主疾病 ：次数
                if (map.containsKey(log.getDiagnosisName())) {
                    map.put(log.getDiagnosisName(), map.get(log.getDiagnosisName()) + 1);
                } else {
                    map.put(log.getDiagnosisName(), 1);
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
        List<Object[]> list = smHosptailLogRepService.getCountByDiagnosisNameAndDeptCode(deptId, startTime, endTime, pageable);
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = list.get(i);
            map.put(objects[0].toString(), Integer.valueOf(objects[1].toString()));
        }
        return map;

    }


    //科室分布功能
    public List<LogBean> countByDept(int year) {
        Map<String, LogBean> map = new HashMap<>();
        List<SmHosptailLog> logList = smHosptailLogRepService.getDeptCountByYear(DateFormatUtil.getYearFirst(year), DateFormatUtil.getYearLast(year));
        for (SmHosptailLog smHosptailLog : logList) {
            if (map.containsKey(smHosptailLog.getDeptCode())) {
                LogBean bean = map.get(smHosptailLog.getDeptCode());
                if (blue.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setBlue(bean.getBlue() + 1);
                } else if (yellow.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setYellow(bean.getYellow() + 1);
                } else if (orange.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setOrange(bean.getOrange() + 1);
                } else if (red.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setRed(bean.getRed() + 1);
                } else {
                    logger.info("此规则无预警等级" + smHosptailLog.toString());
                }
                map.put(smHosptailLog.getDeptCode(), bean);
            } else {
                LogBean bean = new LogBean();
//                bean.setDoctorId(smHosptailLog.getDoctorId());
//                bean.setDoctorName(smHosptailLog.getDoctorName());
                bean.setDeptId(smHosptailLog.getDeptCode());
                if (blue.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setBlue(1);
                } else if (yellow.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setYellow(1);
                } else if (orange.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setOrange(1);
                } else if (red.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setRed(1);
                } else {
                    logger.info("此规则无预警等级" + smHosptailLog.toString());
                }
                map.put(smHosptailLog.getDeptCode(), bean);
            }
        }
        List<LogBean> resultList = new ArrayList<>();
        for (Map.Entry<String, LogBean> entry : map.entrySet()) {
            resultList.add(entry.getValue());
        }

        return resultList;
    }

    public List<String> mapDeptNames() {
        List<String> countByDistinctDeptCode = smHosptailLogRepService.getCountByDistinctDeptCode();
        return countByDistinctDeptCode;
    }


    //人员分布功能
    public List<LogBean> countByDoctor(String deptId, int year) {
        Map<String, LogBean> map = new HashMap<>();
        List<SmHosptailLog> logList = smHosptailLogRepService.getAllByDeptAndYear(deptId, DateFormatUtil.getYearFirst(year), DateFormatUtil.getYearLast(year));
        for (SmHosptailLog smHosptailLog : logList) {
            if (map.containsKey(smHosptailLog.getDoctorId())) {
                LogBean bean = map.get(smHosptailLog.getDoctorId());
                if (blue.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setBlue(bean.getBlue() + 1);
                } else if (yellow.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setYellow(bean.getYellow() + 1);
                } else if (orange.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setOrange(bean.getOrange() + 1);
                } else if (red.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setRed(bean.getRed() + 1);
                } else {
                    logger.info("此规则无预警等级" + smHosptailLog.toString());
                }
                map.put(smHosptailLog.getDoctorId(), bean);
            } else {
                LogBean bean = new LogBean();
                bean.setDoctorId(smHosptailLog.getDoctorId());
                bean.setDoctorName(smHosptailLog.getDoctorName());
                bean.setDeptId(smHosptailLog.getDeptCode());
                if (blue.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setBlue(1);
                } else if (yellow.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setYellow(1);
                } else if (orange.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setOrange(1);
                } else if (red.equals(smHosptailLog.getAlarmLevel())) {
                    bean.setRed(1);
                } else {
                    logger.info("此规则无预警等级" + smHosptailLog.toString());
                }
                map.put(smHosptailLog.getDoctorId(), bean);
            }
        }
        List<LogBean> resultList = new ArrayList<>();
        for (Map.Entry<String, LogBean> entry : map.entrySet()) {
            resultList.add(entry.getValue());

        }

        return resultList;
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
        Map<String, Object> params = new HashMap<>();
        if (deptName != null) {
            params.put("deptCode", deptName);
        }
        //发送
        Specification sf = getWhereClause(startTime, endTime, params);

        List<SmHosptailLog> all = smHosptailLogRepService.findAll(sf);
        return "";

    }

    //住院预警次数
    public Map<String, Integer> countForField(String startTime, String endTime, String deptName, String field) {
        Map<String, Object> params = new HashMap<>();
        if (deptName != null) {
            params.put("deptCode", deptName);
        }
        params.put("warnSource", field);
        //发送
        Specification sf = getWhereClause(startTime, endTime, params);

        List<SmHosptailLog> all = smHosptailLogRepService.findAll(sf);
        Map<String, Integer> map = new HashMap<>();
        for (SmHosptailLog smHosptailLog : all) {
            Date createTime = smHosptailLog.getCreateTime();
            String format = DateFormatUtil.format(createTime, "yyyy-MM");
            if (map.containsKey(format)) {
                map.put(format, map.get(format) + 1);
            } else {
                map.put(format, 1);

            }
        }
        return map;

    }

    //门诊预警次数
    public String countForClinic(String startTime, String endTime, String deptName) {
        Map<String, Object> params = new HashMap<>();
        if (deptName != null) {
            params.put("deptCode", deptName);
        }
        params.put("warnSource", "门诊");

        //发送
        Specification sf = getWhereClause(startTime, endTime, params);

        List<SmHosptailLog> all = smHosptailLogRepService.findAll(sf);
        return "";

    }


    public Specification<T> getWhereClause(String startTime, String endTime, Map<String, Object> params) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (startTime != null) {
                    list.add(cb.greaterThan(root.get("createTime").as(Date.class), DateFormatUtil.parseDate(startTime, "yyyy-MM-dd")));

                }
                if (endTime != null) {
                    list.add(cb.lessThan(root.get("createTime").as(Date.class), DateFormatUtil.parseDate(endTime, "yyyy-MM-dd")));

                }
                //拼接传入参数
                if (params != null) {
                    for (String key : params.keySet()) {
                        if (WebPage.PAGE_NUM.equals(key)) {
                            continue;
                        } else {
                            Object value = params.get(key);
                            if (!org.springframework.util.StringUtils.isEmpty(value.toString())) {
                                list.add(cb.equal(root.get(key), value));
                            }
                        }
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                list.toArray(p);
                return cb.and(p);

            }
        };
    }

    public SmHosptailLog addLog(String map) {
        SmHosptailLog smHosptailLog = new SmHosptailLog();
        Map<String, Object> jsonObject = (Map) JSON.parseObject(map);
        Object doctor_id = jsonObject.get("doctor_id");
        smHosptailLog.setDoctorId(ObjectUtils.flagObj(doctor_id));
        Object dept_id = jsonObject.get("dept_code");
        smHosptailLog.setDeptCode(ObjectUtils.flagObj(dept_id));
        //病人id
        Object patient_id = jsonObject.get("patient_id");
        smHosptailLog.setPatientId(ObjectUtils.flagObj(patient_id));
        Object visit_id = jsonObject.get("visit_id");
        smHosptailLog.setVisitId(ObjectUtils.flagObj(visit_id));
        Object shouyezhenduan = jsonObject.get("shouyezhenduan");
        //主诊断
        String affirmSickness = "";
        //判断首页诊断如果不为空，确认主疾病为首页诊断
        if (shouyezhenduan != null) {
            JSONArray objects = JSONArray.parseArray(shouyezhenduan.toString());
            Iterator<Object> iterator = objects.iterator();
            while (iterator.hasNext()) {
                {
                    Map<String, String> next = (Map) iterator.next();
                    if (next.get("diagnosis_num").equals("1")) {
                        affirmSickness = next.get("diagnosis_name").trim();
                    }

                }
            }
            if (!"".equals(affirmSickness)) {
                smHosptailLog.setDiagnosisName(affirmSickness);
            }
        } else {
            Object binglizhenduan = jsonObject.get("binglizhenduan");
            if (binglizhenduan != null) {
                JSONArray objects = JSONArray.parseArray(binglizhenduan.toString());
                Iterator<Object> iterator = objects.iterator();
                while (iterator.hasNext()) {
                    {
                        Map<String, String> next = (Map) iterator.next();
                        if (next.get("diagnosis_num").equals("1")) {
                            affirmSickness = next.get("diagnosis_name").trim();
                        }

                    }
                }
            }
            if ("".equals(affirmSickness) || affirmSickness == null) {
                logger.debug("确认主疾病存在问题！" + map.toString());
            }
            smHosptailLog.setDiagnosisName(affirmSickness);
        }
        smHosptailLog.setCreateTime(new Date());
        return smHosptailLog;

    }


}

