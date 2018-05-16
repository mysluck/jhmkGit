package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    public String addRule(String rule) {
        return "ok";
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
//        SendRule sendRule = new SendRule();
//        BeanUtils.copyProperties(fill, sendRule);
//        System.out.println(sendRule.toString());
//        List<Map<String, String>> binglizhenduan = fill.getBinglizhenduan();
//        Collections.sort(binglizhenduan, CompareUtil.createComparatorForMap(-1, "diagnosis_time"));
//        List<Map<String, String>> blzdList = getRecentList(binglizhenduan, "diagnosis_name", "diagnosis_time");
//
//        sendRule.setBinglizhenduan(blzdList);
//        List<Map<String, String>> shouyezhenduan = fill.getShouyezhenduan();
//        Collections.sort(shouyezhenduan, CompareUtil.createComparatorForMap(-1, "diagnosis_time"));
//        List<Map<String, String>> shzdList = getRecentList(shouyezhenduan, "diagnosis_name", "diagnosis_time");
//        sendRule.setShouyezhenduan(shzdList);
//        //检查报告
//        List<Map<String, String>> jianchabaogao = fill.getRjianchabaogao();
//        Collections.sort(jianchabaogao, CompareUtil.createComparatorForMap(-1, "exam_time"));
//        List<Map<String, String>> jcList = getRecentList(jianchabaogao, "exam_item_name", "exam_time");
//        sendRule.setJianchabaogao(jcList);
//        //检验报告
//        List<Map<String, String>> jianyanbaogao = fill.getRjianyanbaogao();
//        Collections.sort(jianyanbaogao, CompareUtil.createComparatorForMap(-1, "report_time"));
//        List<Map<String, String>> jyList = getRecentList(jianyanbaogao, "lab_item_name", "report_time");
//
//        sendRule.setJianyanbaogao(jyList);
//        //医嘱
//        List<Map<String, String>> yizhu = fill.getYizhu();
//        Collections.sort(yizhu, CompareUtil.createComparatorForMap(-1, "order_begin_time"));
//        sendRule.setYizhu(yizhu.get(0));
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
    public List<FormatRule> formatData(String forObject) {
        List<FormatRule> list = new LinkedList<>();
        Map<String, String> recieveData = (Map) JSON.parse(forObject.toString());

        Object recieveOldData = recieveData.get("result");
        JSONArray oldData = (JSONArray) recieveOldData;
        int size = oldData.size();
        for (int i = 0; i < size; i++) {
            FormatRule formatRule = new FormatRule();
            Map<String, String> obj = (Map) JSON.toJSON(oldData.get(i));
            String ruleCondition1 = obj.get("ruleCondition");
            System.out.println(ruleCondition1);
            if (obj.get("ruleCondition") != null) {
                String ruleCondition = obj.get("ruleCondition");
                formatRule = MapUtil.map2Bean(obj, FormatRule.class);
                // 解析"ruleCondition" -> "(门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)"
                String condition = disposeRule(ruleCondition);
                formatRule.setRuleCondition(condition);
                list.add(formatRule);
            }
        }
        return list;
    }

    /**
     * @param ruleCondition (门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)
     * @return 症状名称等于高血压&医嘱项编码等于aaa
     */
    private String disposeRule(String ruleCondition) {
        System.out.println(ruleCondition);
        //切割 根据and连接符  原始：(门诊病历_主诉_症状名称等于高血压and医嘱_医嘱_医嘱项编码等于aaa)
        String s = ruleCondition.replaceAll("\\(|\\)", "");
        System.out.println(s);
        ruleCondition.replace(")", "");
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


}
