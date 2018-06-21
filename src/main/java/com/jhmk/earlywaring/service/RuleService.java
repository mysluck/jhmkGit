package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.controller.RuleController;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.UserDataModelMapping;
import com.jhmk.earlywaring.entity.UserModel;
import com.jhmk.earlywaring.entity.repository.service.SmHosptailLogRepService;
import com.jhmk.earlywaring.entity.repository.service.UserDataModelMappingRepService;
import com.jhmk.earlywaring.entity.repository.service.UserModelRepService;
import com.jhmk.earlywaring.entity.rule.*;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import com.jhmk.earlywaring.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
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
    SmHosptailLogRepService smHosptailLogRepService;

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);


    ExecutorService exec = Executors.newFixedThreadPool(32);


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
                String condition = "";
                if (ruleCondition.contains(")and(")) {
                    condition = disposeRuleList(ruleCondition);
                } else {
                    condition = disposeRule(ruleCondition);
                }
                formatRule.setRuleCondition(condition);
                list.add(formatRule);
            }
        }
        map.put("result", list);
        return map;
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
            Optional.ofNullable(userModelRepService.findByUmNameAndUmHospitalName(field.substring(field.lastIndexOf("_") + 1), "bysy"))
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

    /**
     * @param map        规则信息
     * @param fromSource 规则来源
     * @return
     */
    public AtResponse ruleMatch(String map, String fromSource) {
        Callable<AtResponse> callable = null;
        callable = new Callable<AtResponse>() {
            @Override
            public AtResponse call() throws Exception {
                String s2 = map.replaceAll("\\(", "（").replaceAll("\\)", "）");
                JSONObject parse = JSONObject.parseObject(s2);
                ReciveRule fill = ReciveRule.fill(parse);

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

                Object o = JSONObject.parse(JSONObject.toJSONString(fill));
                AtResponse resp = new AtResponse();
                String data = "";
                try {
                    data = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.matchrule, o, String.class);
                    Map<String, Object> result = (Map<String, Object>) JSON.parseObject(data);
                    if ("200".equals(result.get("code")) && !"[]".equals(result.get("result"))) {
                        JSONArray array = (JSONArray) result.get("result");
                        if (result.get("result") != null && array.size() > 0) {
                            resp.setMessage(BaseConstants.SUCCESS);
                            resp.setData(result.get("result"));
                            if (result.get("result") != null) {
                                //todo 预警等级需要返回
                                Object resultData = result.get("result");
                                JSONArray ja = (JSONArray) resultData;
                                if (ja.size() > 0) {
                                    SmHosptailLog smHosptailLog = hosptailLogService.addLog(s2);
                                    Iterator<Object> iterator = ja.iterator();
                                    while (iterator.hasNext()) {
                                        Object next = iterator.next();
                                        Map<String, String> datamap = (Map) next;
                                        //预警等级
                                        String warninglevel = datamap.get("warninglevel");
                                        smHosptailLog.setAlarmLevel(warninglevel);
                                        //释义
                                        smHosptailLog.setHintContent(datamap.get("hintContent"));
                                        smHosptailLog.setSignContent(datamap.get("signContent"));
                                        smHosptailLog.setRuleSource(datamap.get("ruleSource"));
                                        //获取触发的规则id
                                        smHosptailLog.setRuleId(datamap.get("_id"));
                                        smHosptailLogRepService.save(smHosptailLog);
                                    }
                                }

                            }

                        } else {
                            logger.info("没有匹配到规则！");
                        }
                        resp.setResponseCode(ResponseCode.OK);
                    } else {
                        logger.info("规则匹配失败！" + data);
                        logger.info("原始数据：" + o.toString());
                        resp.setResponseCode(ResponseCode.INERERROR4);
                    }
                } catch (Exception e) {
                    logger.info("cdss服务器规则匹配失败！" + e.getMessage());
                    logger.info("原始数据：" + o.toString());
                    resp.setResponseCode(ResponseCode.INERERROR4);
                }
                return resp;
            }
        };
        Future<AtResponse> submit = exec.submit(callable);
        AtResponse s = null;
        try {
            s = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return s;
    }

    public List<Zhenduan> getZhenduan(Zhenduan b) {
        Set<Zhenduan> binglizhenduanSet = new HashSet<>();
        Map<String, String> param = new HashMap<>();
        param.put("diseaseName", b.getDiagnosis_name());
        Object parse1 = JSONObject.toJSON(param);
        String sames = restTemplate.postForObject(urlConfig.getMed() + BaseConstants.getSamilarWord, parse1, String.class);
        if (sames != null && !"[]".equals(sames.trim())) {
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
//        String parentList = restTemplate.postForObject(urlConfig.getMed() + BaseConstants.getParentList, parse1, String.class);
//        if (parentList != null && !"[]".equals(parentList)) {
//            JSONArray objects = JSONArray.parseArray(parentList);
//            Iterator<Object> iterator = objects.iterator();
//            while (iterator.hasNext()) {
//                Object next = iterator.next();
//                Zhenduan newBlzd = new Zhenduan();
//                BeanUtils.copyProperties(b, newBlzd);
//                newBlzd.setDiagnosis_name(next.toString());
//                binglizhenduanSet.add(newBlzd);
//            }
//        }
        List<Zhenduan> list = new ArrayList<Zhenduan>(binglizhenduanSet);
        return list;
    }

}
