package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.entity.SmHospitalLog;
import com.jhmk.earlywaring.entity.SmShowLog;
import com.jhmk.earlywaring.entity.repository.service.SmHospitalLogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmShowLogRepService;
import com.jhmk.earlywaring.entity.repository.service.UserDataModelMappingRepService;
import com.jhmk.earlywaring.entity.repository.service.UserModelRepService;
import com.jhmk.earlywaring.entity.rule.*;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.MapUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.*;

@Service
public class RuleService {

    @Autowired
    UserDataModelMappingRepService userDataModelMappingRepService;
    @Autowired
    UserModelRepService userModelRepService;
    @Autowired
    UserModelService userModelService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UrlConfig urlConfig;
    @Autowired
    HosptailLogService hosptailLogService;
    @Autowired
    SmHospitalLogRepService smHospitalLogRepService;
    @Autowired
    SmShowLogRepService smShowLogRepService;
    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);


    ExecutorService exec = Executors.newFixedThreadPool(32);

    public final String symbol = "[]";
    public final String resultSym = "result";

    /**
     * 去重  获取字段名唯一 日期最大的 map
     *
     * @param formData
     * @param judgeField 去重字段名
     * @param dateField  日期字段名 用来判断时间大小
     * @return
     */
    private List<Map<String, String>> getRecentList(List<Map<String, String>> formData, String judgeField, String dateField) {
        List<Map<String, String>> resultList = new LinkedList<>();
        Map<String, Map<String, String>> onlyMap = new HashMap<>();
        for (Map<String, String> map : formData) {
            //map中存在此类检验、检查报告名  则判断检验日期 取最近
            if (onlyMap.containsKey(map.get(judgeField))) {
                Map<String, String> map1 = onlyMap.get(dateField);
                if ((map1.get(dateField).compareTo(map.get(dateField))) == 1) {
                    onlyMap.put(map.get(judgeField), map);
                }
            } else {
                onlyMap.put(map.get(judgeField), map);
            }
        }

        for (Map.Entry<String, Map<String, String>> entries : onlyMap.entrySet()) {
            resultList.add(entries.getValue());
        }
        return resultList;
    }

    //解析所有原规则（用于界面规则显示）
    public Map<String, Object> formatData(String forObject) {
        Map<String, Object> map = new HashMap<>();
        List<FormatRule> list = new LinkedList<>();
        Map<String, String> recieveData = (Map) JSON.parse(forObject.toString());

        Object recieveOldData = recieveData.get("result");
        Map<String, Object> thisMap = (Map) recieveOldData;
        Object all_page = thisMap.get("all_page");
        map.put("all_page", all_page);
        Object count = thisMap.get("count");
        if (count != null) {
            map.put("count", count);
        }
        Object decisions = thisMap.get("decisions");
        JSONArray oldData = (JSONArray) decisions;
        int size = oldData.size();
        for (int i = 0; i < size; i++) {
            FormatRule formatRule = new FormatRule();
            Map<String, String> obj = (Map) JSON.toJSON(oldData.get(i));
            if (obj.get("ruleCondition") != null) {
                String ruleCondition = obj.get("ruleCondition");
                formatRule = MapUtil.map2Bean(obj, FormatRule.class);
                // 解析"ruleCondition" -> "(门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)"
//                String condition = disposeRule(ruleCondition);
                String condition = disposeRuleCondition(ruleCondition);
                formatRule.setRuleCondition(condition);
                list.add(formatRule);
            }
        }
        map.put("result", list);
        return map;
    }


    /**
     * 解析规则条件
     *
     * @param ruleCondition
     * @return
     */
    public String disposeRuleCondition(String ruleCondition) {
        String condition = "";
        if (ruleCondition.contains(")and(")) {
            condition = disposeRuleList(ruleCondition);
        } else {
            condition = disposeRule(ruleCondition);
        }
        return condition;
    }

    /**
     * @param ruleCondition (门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)
     * @return 症状名称等于高血压&医嘱项编码等于aaa
     */
    private String disposeRule(String ruleCondition) {
        //切割 根据and连接符  原始：(门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)

        String s = ruleCondition.replaceAll("\\(|\\)", "");
        s = s.trim();
        String[] ands = s.split("and");
        StringBuffer sb = new StringBuffer();
        ;
        for (int i = 0; i < ands.length; i++) {
            String condition = ands[i];

            String substring = condition.substring(condition.lastIndexOf("_") + 1);
            if (condition.contains("入院记录_")) {
                sb.append("(入院记录)");
            }
            sb.append(substring);

            if (i != ands.length - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    private String disposeRuleList(String ruleConditions) {
        //切割 根据and连接符  原始：(门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)

        String[] ands = ruleConditions.split("\\)and\\(");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < ands.length; i++) {
            String condition = ands[i];
            String s = disposeRule(condition);
            list.add(s);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {

            sb.append("(").append(list.get(i)).append(")");
            if (i != list.size() - 1) {
                sb.append("and");
            }
        }
        return sb.toString();
    }


    /**
     * 解析现有规则，用于前台重新编辑显示
     *
     * @param data
     * @return
     */
    public List<AnalyzeBean> restoreRule(String data) {
        List<AnalyzeBean> objects = JSON.parseObject(data, new TypeReference<List<AnalyzeBean>>() {
        });
        List<AnalyzeBean> result = new LinkedList<>();
        for (int i = 0; i < objects.size(); i++) {

            AnalyzeBean analyzeBean = objects.get(i);
            String field = analyzeBean.getField();
            Optional.ofNullable(userModelRepService.findByUmName(field.substring(field.lastIndexOf("_") + 1)))
                    .ifPresent(s -> {
                        analyzeBean.setUmType(s.getUmType());
                    });

            //java8
            Optional.ofNullable(userDataModelMappingRepService.findByDmNamePath(field))
                    .ifPresent(s -> {
                        analyzeBean.setField(s.getUmNamePath());
                    });

            Optional.ofNullable(analyzeBean.getValue()).ifPresent(s -> {
                String value = s.get(0);
                analyzeBean.setValues(value);
            });
            result.add(analyzeBean);
        }
        return result;
    }

    public String getId(String forObject) {
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        String code = jsonObject.getString("code");
        if (BaseConstants.OK.equals(code)) {
            Object result = jsonObject.get("result");
            if (result != null) {
                Map<String, String> map = (Map) result;
                String id = map.get("id");
                return id;
            }
        }
        return "";
    }


    public String ruleMatchGetResp(ReciveRule fill) {
        //todo 获取疾病同义词，用于跑医院数据到数据库
//        ReciveRule sameZhenDuanList = getSameZhenDuanList(fill);
        Object o = JSONObject.parse(JSONObject.toJSONString(fill));
        String data = "";
        try {
            data = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.matchrule, o, String.class);
        } catch (Exception e) {
            logger.info("规则匹配失败：原因：" + e.getMessage() + data);
        }
        return data;
    }


    private ReciveRule getSameZhenDuanList(ReciveRule fill) {
        List<Zhenduan> binglizhenduan = fill.getBinglizhenduan();
        if (binglizhenduan != null) {
            Set<Zhenduan> set = new HashSet<>();
            List<Zhenduan> zhenduans = new ArrayList<>();
            for (Zhenduan b : binglizhenduan) {
                List<Zhenduan> zhenduan = getZhenduan(b);
                set.addAll(zhenduan);
            }
            zhenduans.addAll(set);
            fill.setBinglizhenduan(zhenduans);
        }
        return fill;
    }

    /**
     * @param data     规则匹配成功返回信息
     * @param fromData 原始json数据
     */
    public void add2ShowLog(ReciveRule data, String fromData) {
        List<SmShowLog> datalist = new ArrayList<>();
        String doctor_id = data.getDoctor_id();
        String patient_id = data.getPatient_id();
        String visit_id = data.getVisit_id();
        if (StringUtils.isNotBlank(patient_id) && StringUtils.isNotBlank(doctor_id)) {

            Map<String, Object> parse = (Map) JSON.parse(fromData);
            Object result = parse.get("result");
            if (ObjectUtils.anyNotNull(result) && !symbol.equals(result)) {

                JSONArray array = (JSONArray) result;
                Iterator<Object> iterator = array.iterator();
                while (iterator.hasNext()) {
                    JSONObject jsonObject = (JSONObject) iterator.next();
                    String classification = jsonObject.getString("classification");
                    if (!("诊断预警".equals(classification) || "合理用药".equals(classification) || "药品预警".equals(classification))) {
                        continue;
                    }
                    String id = jsonObject.getString("_id");
                    SmShowLog log = smShowLogRepService.findFirstByDoctorIdAndPatientIdAndRuleIdAndVisitId(doctor_id, patient_id, id,visit_id);
                    if (log != null) {
                        continue;
                    }
                    if (jsonObject.keySet().contains("diseaseMessageMap")) {
                        Object diseaseMessageMap = jsonObject.get("diseaseMessageMap");
                        if (ObjectUtils.anyNotNull(diseaseMessageMap)) {
                            JSONArray diseaseMessageArray = (JSONArray) diseaseMessageMap;
                            Iterator<Object> iterator1 = diseaseMessageArray.iterator();
                            SmShowLog hintMesRule = new SmShowLog();
                            Set<String> orderItemNames = new HashSet<>();
                            while (iterator1.hasNext()) {
                                JSONObject next = (JSONObject) iterator1.next();
                                String diagnosis_name = "";
                                String diagnosis_num = "";
                                String sex_name = "";
                                //过敏药物
                                String drug_allergy_name = "";
                                //医嘱
                                String order_item_name = "";
                                //入院记录的检验子项目 和值
                                String zylab_sub_item_name = "";
                                String zylab_result_value = "";
                                //报告的子项目和值
                                String bglab_sub_item_name = "";
                                String bglab_result_value = "";
                                Set<String> keyNames = next.keySet();
                                for (String names : keyNames) {
                                    if (names.contains("diagnosis_name")) {
                                        diagnosis_name = names;
                                    } else if (names.contains("diagnosis_num")) {
                                        diagnosis_num = names;
                                    } else if (names.contains("sex_name")) {
                                        sex_name = names;
                                    } else if (names.contains("order_item_name")) {
                                        order_item_name = names;
                                    } else if (names.contains("ruyuanjilu.auxiliary_examination.lab.lab_sub_item_name")) {
                                        zylab_sub_item_name = names;
                                    } else if (names.contains("ruyuanjilu.auxiliary_examination.lab.lab_result_value")) {
                                        zylab_result_value = names;
                                    } else if (names.contains("jianyanbaogao.lab_report.lab_sub_item_name")) {
                                        bglab_sub_item_name = names;
                                    } else if (names.contains("jianyanbaogao.lab_report.lab_result_value")) {
                                        bglab_result_value = names;
                                    } else if (names.contains("drug_allergy_name")) {
                                        drug_allergy_name = names;
                                    }

                                    //盘段医嘱
                                    if (StringUtils.isNotBlank(next.getString(order_item_name))) {
                                        orderItemNames.add(next.getString(order_item_name));
                                    }
                                }
                                //判断主诊断或其他诊断
                                if (StringUtils.isNotBlank(next.getString(diagnosis_name))) {
                                    if (StringUtils.isNotBlank(next.getString(diagnosis_num)) && "1".equals(next.getString(diagnosis_num))) {
                                        hintMesRule.setMainIllName(next.getString(diagnosis_name));
                                    } else {
                                        hintMesRule.setOtherIllName(next.getString(diagnosis_name));
                                    }
                                }
                                //判断性别
                                if (StringUtils.isNotBlank(next.getString(sex_name))) {
                                    hintMesRule.setSex(next.getString(sex_name));
                                }
                                //过敏药物
                                if (StringUtils.isNotBlank(next.getString(drug_allergy_name))) {
                                    hintMesRule.setDrugAllergyName(next.getString(drug_allergy_name));
                                }
                                Map<String, String> otherMap = new HashMap<>();

                                //判断入院记录检查细项和值
                                String baogaoKeyName = "";
                                String baogaoKeyValue = "";
                                String ruyuanKeyName = "";
                                String ruyuanKeyValue = "";
                                if (StringUtils.isNotBlank(next.getString(zylab_sub_item_name))) {
                                    baogaoKeyName = next.getString(zylab_sub_item_name);
                                }
                                if (StringUtils.isNotBlank(next.getString(zylab_result_value))) {
                                    baogaoKeyValue = next.getString(zylab_result_value);
                                }
                                //判断检验检查细项和值
                                if (StringUtils.isNotBlank(next.getString(bglab_sub_item_name))) {
                                    ruyuanKeyName = next.getString(bglab_sub_item_name);
                                }
                                if (StringUtils.isNotBlank(next.getString(bglab_result_value))) {
                                    ruyuanKeyValue = next.getString(bglab_result_value);
                                }
                                if (StringUtils.isNotBlank(baogaoKeyName) && StringUtils.isNotBlank(baogaoKeyValue)) {
                                    otherMap.put(baogaoKeyName, baogaoKeyValue);
                                }
                                if (StringUtils.isNotBlank(ruyuanKeyName) && StringUtils.isNotBlank(ruyuanKeyValue)) {
                                    otherMap.put(ruyuanKeyName, ruyuanKeyValue);
                                }
                                if (otherMap.size() > 0) {
                                    hintMesRule.setOtherMap(JSONObject.toJSONString(otherMap));
                                }

                                hintMesRule.setDate(DateFormatUtil.format(new Date(), DateFormatUtil.DATETIME_PATTERN_SS));
                                //新添加规则默认0
                                hintMesRule.setDoctorId(doctor_id);
                                hintMesRule.setPatientId(patient_id);
                                hintMesRule.setVisitId(visit_id);
                                hintMesRule.setRuleId(id);
                                hintMesRule.setRuleStatus(0);
                                //用于区分规则匹配添加和诊疗提醒（cdss）添加
                                hintMesRule.setType("rulematch");
                            }
                            if (orderItemNames.size() > 0) {
                                hintMesRule.setOrderItemNames(JSONObject.toJSONString(orderItemNames));
                            }
                            datalist.add(hintMesRule);
                        }
                    }
                }
            }
            smShowLogRepService.save(datalist);
        }

    }

    //如果匹配到规则，解析规则匹配的返回数据入库 hostpital表
    public void add2LogTable(String resultData, String mes) {
        JSONObject jsonObject = JSONObject.parseObject(resultData);
        if (!symbol.equals(jsonObject.getString(resultSym))) {
            Object result = jsonObject.get(resultSym);
            SmHospitalLog smHospitalLog = hosptailLogService.addLog(mes);
            if (ObjectUtils.anyNotNull(result)) {
                //todo 预警等级需要返回
                JSONArray ja = (JSONArray) result;
                if (ja.size() > 0) {
                    Iterator<Object> iterator = ja.iterator();
                    while (iterator.hasNext()) {
                        Object next = iterator.next();
                        Map<String, String> datamap = (Map) next;
                        //预警等级
                        String warninglevel = datamap.get("warninglevel");
                        smHospitalLog.setAlarmLevel(warninglevel);
                        //释义
                        smHospitalLog.setHintContent(datamap.get("hintContent"));
                        smHospitalLog.setSignContent(datamap.get("signContent"));
                        smHospitalLog.setRuleSource(datamap.get("ruleSource"));
                        smHospitalLog.setClassification(datamap.get("classification"));
                        smHospitalLog.setIdentification(datamap.get("identification"));
                        String ruleCondition = datamap.get("ruleCondition");
                        if (StringUtils.isNotBlank(ruleCondition)) {
                            String condition = disposeRuleCondition(ruleCondition);
                            smHospitalLog.setRuleCondition(condition);
                        }
                        smHospitalLog.setCreateTime(new Date());
                        //获取触发的规则id
                        smHospitalLog.setRuleId(datamap.get("_id"));
                        SmHospitalLog save = smHospitalLogRepService.save(smHospitalLog);
                        if (save == null) {
                            logger.info("入日志库失败:" + save.toString());
                        }
                    }
                }
            }
        }

    }

    /**
     * @param map        规则信息
     * @param fromSource 规则来源
     * @return
     */
//    public AtResponse ruleMatch(String map, String fromSource) {
//        Callable<AtResponse> callable = null;
//        callable = new Callable<AtResponse>() {
//            @Override
//            public AtResponse call() throws Exception {
//                //todo  重写解析规则
//                String s = anaRule(map);
//                String s2 = s.replaceAll("\\(", "（").replaceAll("\\)", "）");
//                JSONObject parse = JSONObject.parseObject(s2);
//                ReciveRule fill = ReciveRule.fill(parse);
//
//                //todo 获取疾病同义词，用于跑医院数据到数据库
////                List<Zhenduan> binglizhenduan = fill.getBinglizhenduan();
////                if (binglizhenduan != null) {
////                    Set<Zhenduan> set = new HashSet<>();
////                    List<Zhenduan> zhenduans = new ArrayList<>();
////                    for (Zhenduan b : binglizhenduan) {
////                        List<Zhenduan> zhenduan = getZhenduan(b);
////                        set.addAll(zhenduan);
////                    }
////                    zhenduans.addAll(set);
////                    fill.setBinglizhenduan(zhenduans);
////                }
//
//                Object o = JSONObject.parse(JSONObject.toJSONString(fill));
//                AtResponse resp = new AtResponse();
//                String data = "";
//                try {
//                    data = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.matchrule, o, String.class);
//                    Map<String, Object> result = (Map<String, Object>) JSON.parseObject(data);
//                    if ("200".equals(result.get("code")) && !symbol.equals(result.get(resultSym))) {
//                        JSONArray array = (JSONArray) result.get(resultSym);
//                        if (result.get(resultSym) != null && array.size() > 0) {
//                            resp.setMessage(BaseConstants.SUCCESS);
//                            if (result.get(resultSym) != null) {
//                                Object resultData = result.get(resultSym);
//
//                                List<HintMesRule> list = showMes(data);
//                                Map<String, Object> resultMap = new HashMap<>();
//                                resultMap.put("rule", resultData);
//                                resultMap.put("hint", list);
//                                resp.setData(resultMap);
//
//                                //todo 预警等级需要返回
//                                JSONArray ja = (JSONArray) resultData;
//                                if (ja.size() > 0) {
//                                    SmHospitalLog smHospitalLog = hosptailLogService.addLog(s2);
//                                    Iterator<Object> iterator = ja.iterator();
//                                    while (iterator.hasNext()) {
//                                        Object next = iterator.next();
//                                        Map<String, String> datamap = (Map) next;
//                                        //预警等级
//                                        String warninglevel = datamap.get("warninglevel");
//                                        smHospitalLog.setAlarmLevel(warninglevel);
//                                        //释义
//                                        smHospitalLog.setHintContent(datamap.get("hintContent"));
//                                        smHospitalLog.setSignContent(datamap.get("signContent"));
//                                        smHospitalLog.setRuleSource(datamap.get("ruleSource"));
//                                        smHospitalLog.setClassification(datamap.get("classification"));
//                                        smHospitalLog.setIdentification(datamap.get("identification"));
//                                        //获取触发的规则id
//                                        smHospitalLog.setRuleId(datamap.get("_id"));
//                                        smHospitalLogRepService.save(smHospitalLog);
//                                    }
//                                }
//
//                            }
//
//                        } else {
//                            logger.info("没有匹配到规则！");
//                        }
//
//                        resp.setResponseCode(ResponseCode.OK);
//                    } else {
//                        logger.info("规则匹配失败！" + data);
//                        logger.info("原始数据：" + o.toString());
//                        resp.setResponseCode(ResponseCode.INERERROR4);
//                    }
//                } catch (Exception e) {
//                    logger.info("cdss服务器规则匹配失败！" + e.getMessage());
//                    logger.info("原始数据：" + o.toString());
//                    resp.setResponseCode(ResponseCode.INERERROR4);
//                }
//                return resp;
//            }
//        };
//        Future<AtResponse> submit = exec.submit(callable);
//        AtResponse s = null;
//        try {
//            s = submit.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return s;
//    }

    /**
     * 将英文()括号改为中文（）
     *
     * @param data
     * @return
     */
    public String stringTransform(String data) {
        String s2 = data.replaceAll("\\(", "（").replaceAll("\\)", "）");
        return s2;
    }

    private List<HintMesRule> showMes(String data) {
        Map parse = (Map) JSONObject.parse(data);
        List<HintMesRule> ruleList = new ArrayList<>();
        Optional.ofNullable(parse.get(resultSym)).ifPresent(s -> {
            JSONArray array = (JSONArray) s;
            Iterator<Object> iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                if (!"诊断预警".equals(jsonObject.getString("classification"))) {
                    continue;
                }
                if (jsonObject.keySet().contains("diseaseMessageMap")) {
                    Object diseaseMessageMap = jsonObject.get("diseaseMessageMap");
                    if (ObjectUtils.anyNotNull(diseaseMessageMap)) {
                        JSONArray diseaseMessageArray = (JSONArray) diseaseMessageMap;
                        Iterator<Object> iterator1 = diseaseMessageArray.iterator();
                        HintMesRule hintMesRule = new HintMesRule();
                        while (iterator1.hasNext()) {
                            JSONObject next = (JSONObject) iterator1.next();

                            String diagnosis_name = "";
                            String diagnosis_num = "";
                            String sex_name = "";
                            String order_item_name = "";
                            //入院记录的检验子项目 和值
                            String zylab_sub_item_name = "";
                            String zylab_result_value = "";
                            //报告的子项目和值
                            String bglab_sub_item_name = "";
                            String bglab_result_value = "";
                            Set<String> keyNames = next.keySet();
                            for (String names : keyNames) {
                                if (names.contains("diagnosis_name")) {
                                    diagnosis_name = names;
                                } else if (names.contains("diagnosis_num")) {
                                    diagnosis_num = names;
                                } else if (names.contains("sex_name")) {
                                    sex_name = names;
                                } else if (names.contains("order_item_name")) {
                                    order_item_name = names;
                                } else if (names.contains("ruyuanjilu.auxiliary_examination.lab.lab_sub_item_name")) {
                                    zylab_sub_item_name = names;
                                } else if (names.contains("ruyuanjilu.auxiliary_examination.lab.lab_result_value")) {
                                    zylab_result_value = names;
                                } else if (names.contains("jianyanbaogao.lab_report.lab_sub_item_name")) {
                                    bglab_sub_item_name = names;
                                } else if (names.contains("jianyanbaogao.lab_report.lab_result_value")) {
                                    bglab_result_value = names;
                                }
                            }

                            //判断主诊断或其他诊断
                            if (StringUtils.isNotBlank(next.getString(diagnosis_name))) {
                                if (StringUtils.isNotBlank(next.getString(diagnosis_num)) & "1".equals(next.getString(diagnosis_num))) {
                                    hintMesRule.setMainIllName(next.getString(diagnosis_name));
                                } else {
                                    hintMesRule.setOtherIllName(next.getString(diagnosis_name));
                                }
                            }
                            Map<String, String> otherMap = new HashMap<>();

                            //判断性别
                            if (StringUtils.isNotBlank(next.getString(sex_name))) {
                                hintMesRule.setSex(next.getString(sex_name));
                            }
                            //盘段医嘱
                            if (StringUtils.isNotBlank(next.getString(order_item_name))) {
                                hintMesRule.setYizhu(next.getString(order_item_name));

                            }
                            //判断入院记录检查细项和值
                            String baogaoKeyName = "";
                            String baogaoKeyValue = "";
                            String ruyuanKeyName = "";
                            String ruyuanKeyValue = "";
                            if (StringUtils.isNotBlank(next.getString(zylab_sub_item_name))) {
                                baogaoKeyName = next.getString(zylab_sub_item_name);

                            }
                            if (StringUtils.isNotBlank(next.getString(zylab_result_value))) {
                                baogaoKeyValue = next.getString(zylab_result_value);
                            }
                            //判断检验检查细项和值
                            if (StringUtils.isNotBlank(next.getString(bglab_sub_item_name))) {
                                ruyuanKeyName = next.getString(bglab_sub_item_name);

                            }
                            if (StringUtils.isNotBlank(next.getString(bglab_sub_item_name))) {
                                ruyuanKeyValue = next.getString(bglab_sub_item_name);
                            }

                            if (StringUtils.isNotBlank(baogaoKeyName) && StringUtils.isNotBlank(baogaoKeyValue)) {
                                otherMap.put(baogaoKeyName, baogaoKeyValue);
                            }
                            if (StringUtils.isNotBlank(ruyuanKeyName) && StringUtils.isNotBlank(ruyuanKeyValue)) {
                                otherMap.put(ruyuanKeyName, ruyuanKeyValue);
                            }
                            hintMesRule.setOtherMap(otherMap);
                            hintMesRule.setDate(DateFormatUtil.format(new Date(), DateFormatUtil.DATETIME_PATTERN_SS));

                        }
                        System.out.println(JSONObject.toJSONString(hintMesRule));
                        ruleList.add(hintMesRule);

                    }
                }
            }
        });

        return ruleList;
    }

    /**
     * 解析规则 一诉五史 key value型转为  键值对：
     *
     * @param map
     * @return
     */
    public String anaRule(String map) {
        Map<String, String> paramMap = (Map) JSON.parse(map);
        Map<String, Object> endparamMap = new HashMap<String, Object>();
        endparamMap.putAll(paramMap);
        for (String key : paramMap.keySet()) {
            if ("ruyuanjilu".equals(key)) {
                String ryjl = String.valueOf(paramMap.get("ruyuanjilu"));
                JSONArray jsonArray = JSON.parseArray(ryjl);
                Iterator<Object> it = jsonArray.iterator();
                Map<String, String> ryjlMap = new HashMap<String, String>();
                while (it.hasNext()) {
                    JSONObject ob = (JSONObject) it.next();
                    String ryjlkey = ob.getString("key");
                    String value = ob.getString("value");
                    if (value != null && !value.isEmpty()) {
                        if ("既往史".equals(ryjlkey)) {
                            ryjlMap.put("history_of_past_illness", value);
                        } else if ("主诉".equals(ryjlkey)) {
                            ryjlMap.put("chief_complaint", value);
                        } else if ("现病史".equals(ryjlkey)) {
                            ryjlMap.put("history_of_present_illness", value);
                        } else if ("家族史".equals(ryjlkey)) {
                            ryjlMap.put("history_of_family_member_diseases", value);
                        } else if ("婚姻史".equals(ryjlkey)) {
                            ryjlMap.put("menstrual_and_obstetrical_histories", value);
                        } else if ("辅助检查".equals(ryjlkey)) {
                            ryjlMap.put("auxiliary_examination", value);
                        } else if ("体格检查".equals(ryjlkey)) {
                            ryjlMap.put("physical_examination", value);
                        }
                    }
                }
                endparamMap.put("ruyuanjilu", ryjlMap);
            } else if ("jianyanbaogao".equals(key)) {
                String jybg = String.valueOf(paramMap.get("jianyanbaogao"));
                JSONArray jsonArray = JSON.parseArray(jybg);
                Iterator<Object> it = jsonArray.iterator();
                List<Map<String, String>> jybgMap = new ArrayList<Map<String, String>>();
                while (it.hasNext()) {
                    Map<String, String> jcbg = new HashMap<String, String>();
                    JSONObject ob = (JSONObject) it.next();
                    if (ob.containsKey("labTestItems")) {
                        Object labTestItems = ob.get("labTestItems");
                        JSONArray sbjsonArray = JSON.parseArray(labTestItems.toString());
                        for (Object object : sbjsonArray) {
                            JSONObject sbobj = (JSONObject) object;
                            if (sbobj.getString("name") != null) {
                                jcbg.put("lab_sub_item_name", sbobj.getString("name"));
                            }
                            if (sbobj.getString("unit") != null) {
                                jcbg.put("lab_result_value_unit", sbobj.getString("unit"));
                            }
                            if (sbobj.getString("lab_result_value") != null) {
                                jcbg.put("lab_result_value", sbobj.getString("lab_result_value"));
                            }
                            if (sbobj.getString("result_status_code") != null) {
                                jcbg.put("result_status_code", sbobj.getString("result_status_code"));
                            }
                        }
                    }
                    jybgMap.add(jcbg);

                }
                endparamMap.put(key, jybgMap);
            }
        }
        return JSONObject.toJSONString(endparamMap);
    }


    public List<Zhenduan> getZhenduan(Zhenduan b) {
        Set<Zhenduan> binglizhenduanSet = new HashSet<>();
        Map<String, String> param = new HashMap<>();
        param.put("diseaseName", b.getDiagnosis_name());
        Object parse1 = JSONObject.toJSON(param);
        String sames = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.getSamilarWord, parse1, String.class);
        if (sames != null && !symbol.equals(sames.trim())) {
            JSONArray objects = JSONArray.parseArray(sames);
            Iterator<Object> iterator = objects.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                Zhenduan newBlzd = new Zhenduan();
                BeanUtils.copyProperties(b, newBlzd);
                newBlzd.setDiagnosis_name(next.toString());
                binglizhenduanSet.add(newBlzd);
            }
        }
        if (b.getDiagnosis_name() != null && !"".equals(b.getDiagnosis_name().trim())) {
            Zhenduan newBlzd = new Zhenduan();
            BeanUtils.copyProperties(b, newBlzd);
            binglizhenduanSet.add(newBlzd);
        }
        //发送 获取疾病父类
        String parentList = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.getParentList, parse1, String.class);
        if (parentList != null && !symbol.equals(parentList)) {
            JSONArray objects = JSONArray.parseArray(parentList);
            Iterator<Object> iterator = objects.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                Zhenduan newBlzd = new Zhenduan();
                BeanUtils.copyProperties(b, newBlzd);
                newBlzd.setDiagnosis_name(next.toString());
                binglizhenduanSet.add(newBlzd);
            }
        }
        List<Zhenduan> list = new ArrayList<Zhenduan>(binglizhenduanSet);
        return list;
    }

    public void getTipList2ShowLog(ReciveRule fill, String map) throws ExecutionException, InterruptedException {
        String doctor_id = fill.getDoctor_id();
        String patient_id = fill.getPatient_id();
        String visit_id = fill.getVisit_id();
        if (StringUtils.isNotBlank(doctor_id) && StringUtils.isNotBlank(patient_id)) {
            Object o = JSONObject.parse(map);
            String result = "";
            try {

                result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.getTipList, o, String.class);
                if (!symbol.equals(result)) {
                    JSONArray array = JSONArray.parseArray(result);
                    Iterator<Object> iterator = array.iterator();
                    while (iterator.hasNext()) {
                        SmShowLog smShowLog = new SmShowLog();
                        JSONObject next = (JSONObject) iterator.next();
                        String itemName = next.getString("itemName");
                        String type = next.getString("type");
                        String stat = next.getString("stat");

                        SmShowLog isExist = smShowLogRepService.findFirstByDoctorIdAndPatientIdAndItemNameAndTypeAndStatAndVisitId(doctor_id, patient_id, itemName, type, stat,visit_id);
                        if (isExist != null) {
                            continue;
                        }
                        smShowLog.setItemName(itemName);
                        smShowLog.setType(type);
                        smShowLog.setStat(stat);
                        String data = next.getString("data");
//                    smShowLog.setDate(DateFormatUtil.format(new Date(), DateFormatUtil.DATETIME_PATTERN_SS));
                        smShowLog.setDate(data);
                        String significance = next.getString("significance");


                        if (StringUtils.isNotBlank(itemName) || StringUtils.isNotBlank(significance) || !"{}".equals(significance.trim())) {

                            smShowLog.setSignificance(significance);
                            String value = next.getString("value");
                            smShowLog.setValue(value);
                            smShowLog.setRuleStatus(0);
                            smShowLog.setPatientId(patient_id);
                            smShowLog.setDoctorId(doctor_id);
                            smShowLog.setVisitId(visit_id);
                            smShowLog.setDate(DateFormatUtil.format(new Date(), DateFormatUtil.DATETIME_PATTERN_SS));
                            smShowLogRepService.save(smShowLog);
                        }
                    }
                }

            } catch (HttpServerErrorException e) {
                logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}", "getTipList.json", e.getCause(), e.getMessage());
            }
        }else {
            logger.info("医生id或病人id为空,条件为：{}，触发规则为：{}"+fill+map);
        }

    }

}
