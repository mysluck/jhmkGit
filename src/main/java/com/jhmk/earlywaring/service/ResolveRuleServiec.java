package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.entity.SmShowLog;
import com.jhmk.earlywaring.entity.rule.LogMapping;
import com.jhmk.earlywaring.util.DateFormatUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ziyu.zhou
 * @date 2018/8/2 10:37
 */


/**
 * 解析规则
 */
@Service
public class ResolveRuleServiec {

    private static final String symbol = "[]";

    public static void main(String[] args) {
//        triggerRule(s);
    }

    /**
     * 解析触发规则后提示的规则
     */
//    public static void triggerRule(String fromData) {
//        JSONObject jObject = JSON.parseObject(fromData);
//        Object result = jObject.get("result");
//        if (ObjectUtils.anyNotNull(result) && !symbol.equals(result)) {
//            JSONArray array = (JSONArray) result;
//            Iterator<Object> iterator = array.iterator();
//            while (iterator.hasNext()) {
//                JSONObject jsonObject = (JSONObject) iterator.next();
//                String classification = jsonObject.getString("classification");
//                String hintContent = jsonObject.getString("hintContent");
//
//                String id = jsonObject.getString("_id");
//                Object diseaseMessageMap = jsonObject.get("diseaseMessageMap");
//                if (Objects.nonNull(diseaseMessageMap)) {
//                    if (ObjectUtils.anyNotNull(diseaseMessageMap)) {
//                        JSONArray diseaseMessageArray = (JSONArray) diseaseMessageMap;
//                        Iterator<Object> iterator1 = diseaseMessageArray.iterator();
//                        SmShowLog hintMesRule = new SmShowLog();
//                        Set<String> orderItemNames = new HashSet<>();
//                        while (iterator1.hasNext()) {
//                            JSONObject next = (JSONObject) iterator1.next();
//                            Set<String> keyNames = next.keySet();
//                            for (String names : keyNames) {
//                                if (names.contains("diagnosis_name")) {
//                                    LogMapping logMapping = new LogMapping();
//                                    logMapping.setLogObj("诊断名称");
//                                    logMapping.setLogResult(next.getString(names));
//                                } else if (names.contains("diagnosis_num")) {
//                                    diagnosis_num = names;
//                                } else if (names.contains("sex_name")) {
//                                    sex_name = names;
//                                } else if (names.contains("order_item_name")) {
//                                    order_item_name = names;
//                                } else if (names.contains("ruyuanjilu.auxiliary_examination.lab.lab_sub_item_name")) {
//                                    zylab_sub_item_name = names;
//                                } else if (names.contains("ruyuanjilu.auxiliary_examination.lab.lab_result_value")) {
//                                    zylab_result_value = names;
//                                } else if (names.contains("jianyanbaogao.lab_report.lab_sub_item_name")) {
//                                    bglab_sub_item_name = names;
//                                } else if (names.contains("jianyanbaogao.lab_report.lab_result_value")) {
//                                    bglab_result_value = names;
//                                } else if (names.contains("drug_allergy_name")) {
//                                    drug_allergy_name = names;
//                                } else if (names.contains("jiancanbaogao.exam_report.exam_item_code")) {
//                                    exam_item_code = names;
//                                } else if (names.contains("jiancanbaogao.exam_report.exam_diag")) {
//                                    exam_diag = names;
//                                }
//
//                                String nextString = next.getString(names);
//                                System.out.println(names + "=" + nextString);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    static String s =
            "{\n" +
                    "  \"code\": 200,\n" +
                    "  \"message\": \"success\",\n" +
                    "  \"result\": [\n" +
                    "    {\n" +
                    "      \"ruleSource\": \"中国心力衰竭诊断和治疗指南2014\",\n" +
                    "      \"identification\": \"心内科\",\n" +
                    "      \"hintContent\": \"慎用盐酸咪达普利\",\n" +
                    "      \"ruleCondition\": \"((住院病历诊断_诊断名称等于左心衰竭)and(住院医嘱_医嘱项名称等于盐酸咪达普利)and(住院检查报告_通用检查_检查项目名称等于肾动脉造影and住院检查报告_通用检查_检查结论等于双侧肾动脉狭窄))\",\n" +
                    "      \"signContent\": \"左心衰竭患者，肾动脉造影提示双侧肾动脉狭窄时，慎用盐酸咪达普利\",\n" +
                    "      \"diseaseMessageMap\": [\n" +
                    "        {\n" +
                    "          \"binglizhenduan.diagnosis_name@1\": \"左心衰竭\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"yizhu.order_item_name@1\": \"盐酸咪达普利\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"jianchabaogao.exam_report.exam_item_name@1\": \"肾动脉造影\",\n" +
                    "          \"jianchabaogao.exam_report.exam_diag@1\": \"双侧肾动脉狭窄\"\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"_id\": \"346c3f8bef824254bb3e70b21b9f1010\",\n" +
                    "      \"classification\": \"药品预警\",\n" +
                    "      \"warninglevel\": \"橙色预警\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
}
