package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.UserDataModelMapping;
import com.jhmk.earlywaring.entity.UserModel;
import com.jhmk.earlywaring.entity.repository.service.UserDataModelMappingRepService;
import com.jhmk.earlywaring.entity.repository.service.UserModelRepService;
import com.jhmk.earlywaring.entity.rule.AnalyzeBean;
import com.jhmk.earlywaring.entity.rule.FormatRule;
import com.jhmk.earlywaring.entity.rule.ReciveRule;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import com.jhmk.earlywaring.util.MapUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

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


    //添加规则（未提交）
    private static final String UNSUBMITRULE = "";
    //提交规则
    private static final String SUBMITRULE = "";
    //审核通过规则
    private static final String DEPTCOUNT = "";
    private static final String URL3 = null;

    private HttpHeaders getHeader() {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        return headers;
    }

    /**
     * 獲取医生已添加未提交的规则集合
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> getUnSubmitRule(String userId) {
        HttpHeaders headers = getHeader();
        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("department", userId);
        postParameters.put("hospitalname", "测试医院");
        postParameters.put("flag", "patient");
        HttpEntity<String> r = new HttpEntity(postParameters, headers);
        //发送
        String json = restTemplate.postForObject(UNSUBMITRULE, r, String.class);

        JSONObject jsonObject = JSONObject.parseObject(json);
        String code = (String) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        List<Map<String, Object>> result = null;
//        if (BaseConstants.OK.equals(code) && BaseConstants.SUCCESS.equals(message)) {
        result = (List) jsonObject.get("result");
        return result;

    }

    /**
     * 添加规则
     *
     * @param rule
     * @return
     */

    static String s = "{\n" +
            "  \"code\": \"200\",\n" +
            "  \"message\": \"success\",\n" +
            "  \"result\": {\n" +
            "    \"id\": \"4a2c83e0bffe421b93bc262212c8eaad\",\n" +
            "    \"doctorId\": \"10401\",\n" +
            "    \"createTime\": \"2018-05-30\",\n" +
            "    \"ruleConditions\": [\n" +
            "      {\n" +
            "        \"field\": \"住院医嘱_医嘱项名称\",\n" +
            "        \"value\": [\n" +
            "          \"啦啦12\"\n" +
            "        ],\n" +
            "        \"exp\": \"包含\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"field\": \"住院病案首页_就诊信息_年龄值\",\n" +
            "        \"value\": [\n" +
            "          \"77岁\"\n" +
            "        ],\n" +
            "        \"exp\": \"等于\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"field\": \"住院医嘱_医嘱项编码\",\n" +
            "        \"value\": [\n" +
            "          \" 啦啦\"\n" +
            "        ],\n" +
            "        \"exp\": \"等于\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"ruleCondition\": \"(住院医嘱_医嘱项名称包含啦啦12and住院病案首页_就诊信息_年龄值等于77岁and住院医嘱_医嘱项编码等于 啦啦)\",\n" +
            "    \"hintContent\": \"提示内容\",\n" +
            "    \"signContent\": \"规则出处\",\n" +
            "    \"ruleSource\": \"规则出处\",\n" +
            "    \"isSubmit\": false,\n" +
            "    \"sts\": \"A\"\n" +
            "  }\n" +
            "}";

    public String addRule(String rule) {
        Object parse = JSONObject.parse(s);

        return "ok";
    }

    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        String code = jsonObject.getString("code");
        if (BaseConstants.OK.equals(code)) {
            Object result = jsonObject.get("result");
            if (result != null) {
                Map<String, String> map = (Map) result;
                String id = map.get("id");

                System.out.println(result);
            }
        }

    }

    /**
     * 删除规则
     *
     * @param ruleId
     * @return
     */

    public String delRule(String ruleId) {
        return "ok";
    }

    /**
     * 提交规则  修改状态
     *
     * @param id 规则唯一标识
     * @return
     */

    public void subRule(String id) {

    }

    /**
     * 获取提交的规则
     *
     * @param id
     */
    public void getSubmitRule(String id) {

    }

    /**
     * 修改规则
     *
     * @param id
     */
    public void updataSubmitRule(String id) {

    }


    /**
     * 规则匹配
     *
     * @param mes
     */
    public ReciveRule ruleMatch(String mes) {
        JSONObject object = JSON.parseObject(mes);
        ReciveRule fill = ReciveRule.fill(object);
        return fill;
    }


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
                String condition = disposeRule(ruleCondition);
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
        System.out.println(s);
        String[] ands = s.split("and");
        StringBuffer sb = new StringBuffer();
        ;
        for (int i = 0; i < ands.length; i++) {
            String condition = ands[i];
            String substring = condition.substring(condition.lastIndexOf("_") + 1);
            sb.append(substring);
            if (i != ands.length - 1) {
                sb.append("&");
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
}
