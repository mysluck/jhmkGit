package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.controller.StandardRuleController;
import com.jhmk.earlywaring.entity.rule.FormatRule;
import com.jhmk.earlywaring.entity.rule.StandardRule;
import com.jhmk.earlywaring.util.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ziyu.zhou
 * @date 2018/8/8 18:16
 */
@Service
public class StandardRuleService {
    @Autowired
    RuleService ruleService;
    @Autowired
    UrlConfig urlConfig;
    @Autowired
    RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(StandardRuleService.class);

    /**
     * @param basicStandardRuleList 2层 标准规则解析bean
     * @param oldList               保存规则的String
     * @return
     */
    public List<String> addCdssRule(List<List<StandardRule>> basicStandardRuleList, List<String> oldList) {
        //遍历第一层 大并列关系
        for (int i = 0, l = basicStandardRuleList.size(); i < l; i++) {
            List<StandardRule> standardRuleList = basicStandardRuleList.get(i);
            //便利第二层 小并列关系
            for (int j = 0, k = standardRuleList.size(); j < k; j++) {
                //获取标准规则
                StandardRule rule = standardRuleList.get(j);
                oldList = getOneStrRule(oldList, rule);
                List<String> tempList1 = new LinkedList<>();
                if (j == k - 1) {
                    for (String s : oldList) {
                        tempList1.add(s + ")");
                    }
                    oldList = tempList1;

                }
            }

            if (i < l - 1) {
                List<String> tempList1 = new LinkedList<>();
                for (String s : oldList) {
                    tempList1.add(s + "and");
                }
                oldList = tempList1;
            }

        }
        return oldList;
    }

    /**
     * @param oldList
     * @param rule    标准规则
     * @return 得到规则语句 如（诊断名称等于高血压and诊断序号等于1）
     */
    public List<String> getOneStrRule(List<String> oldList, StandardRule rule) {
        //获取所有条件
        List<String> allValues = rule.getAllValues();
        //条件名
        String name = rule.getName();
        //关系
        String sympol = rule.getSympol();
        List<String> tempList = new LinkedList<>();
        for (int m = 0; m < allValues.size(); m++) {
            if (oldList.size() == 0) {
                tempList.add("(" + name + sympol + allValues.get(m));
                //如果已有list的表达式不为空
            } else {
                for (String old : oldList) {
                    //判断结尾是否为and 如果是 添加（ 大并列关系
                    if ("and".equals(old.substring(old.length() - 3))) {
                        tempList.add(old + "(" + name + sympol + allValues.get(m));
                        //如果不是 添加and链接小并列关系
                    } else {
                        tempList.add(old + "and" + name + sympol + allValues.get(m));
                    }
                }

            }
        }
        return tempList;
    }


    /**
     * @param ruleStr 标准规则的element  （(诊断名称等于高血压/高血压一级/高血压二级and医嘱用药等于青霉素)） 得到大并列 小并列关系
     * @return
     */

    public List<List<StandardRule>> getBasicStandardRule(String ruleStr) {
        List<List<StandardRule>> resultList = new LinkedList<>();
        //切分大并列
        String[] ands = ruleStr.split("\\)and\\(");
        for (String str : ands) {
            List<StandardRule> oneRule = getOneRule(str);
            resultList.add(oneRule);
        }
        return resultList;
    }

    //切分小并列 并生成bean的list
    public List<StandardRule> getOneRule(String str) {
        List<StandardRule> list = new LinkedList<>();
        String s = str.replaceAll("\\(", "").replaceAll("\\)", "");
        String[] ands = s.split("and");
        //        for (String oneStr : ands) {
        for (int i = 0, l = ands.length; i < l; i++) {
            StandardRule rule = anaSyspol(ands[i]);
            list.add(rule);
        }
        return list;
    }


    //获取rule bean 判断符号 获取关系
    private StandardRule anaSyspol(String str) {
        if (str.contains(BaseConstants.NCT)) {
            return getRule(str, BaseConstants.NCT);
        } else if (str.contains(BaseConstants.CT)) {
            return getRule(str, BaseConstants.CT);

        } else if (str.contains(BaseConstants.GTE)) {
            return getRule(str, BaseConstants.GTE);

        } else if (str.contains(BaseConstants.LTE)) {
            return getRule(str, BaseConstants.LTE);

        } else if (str.contains(BaseConstants.GT)) {
            return getRule(str, BaseConstants.GT);

        } else if (str.contains(BaseConstants.LT)) {
            return getRule(str, BaseConstants.LT);

        } else if (str.contains(BaseConstants.NEQ)) {
            return getRule(str, BaseConstants.NEQ);

        } else if (str.contains(BaseConstants.EQ)) {
            return getRule(str, BaseConstants.EQ);
        } else {
            System.out.println("解析错误");
            return null;
        }
    }

    //生成单个bean
    private StandardRule getRule(String str, String sympol) {
        StandardRule rule = new StandardRule();
        String[] split = str.split(sympol);
        if (split.length == 2) {
            rule.setName(split[0]);
            rule.setSympol(sympol);
            rule.setStatus("0");
            String[] allSplit = split[1].split("/");
            rule.setStandardValue(allSplit[0]);
            List<String> clildName = new LinkedList<>();
            for (int i = 0; i < allSplit.length; i++) {
                if (i == 0) {
                    continue;
                }
                clildName.add(allSplit[i]);
            }
            rule.setChildValues(clildName);
            rule.setAllValues(Arrays.asList(split[1].split("/")));
        }
        return rule;
    }

    /**
     * 获取规则条件集合 用于标准规则批量删除子规则时 查看删除的条件是否在子规则中存在
     *
     * @param ruleStr
     * @return
     */
    public List<String> getRuleConditions(String ruleStr) {
        List<String> ruleConditions = new LinkedList<>();
        String s = ruleStr.replaceAll("\\(", "").replaceAll("\\)", "");
        String[] ands = s.split("and");
        //        for (String oneStr : ands) {
        for (int i = 0, l = ands.length; i < l; i++) {
            StandardRule rule = anaSyspol(ands[i]);
            String standardValue = rule.getStandardValue();
            ruleConditions.add(standardValue);
        }
        return ruleConditions;
    }

    /**
     * @param field
     * @param standardName 标准规则名
     * @param formatrule
     * @return
     */
    public boolean updataStandardChildElement(String field, String standardName, FormatRule formatrule, boolean flag) {
        String[] childNames = field.split(",");

        // ture 增加
        String childElement = formatrule.getChildElement();
        StringBuffer sb = new StringBuffer();
        String[] ands = childElement.split("and");
        if (flag) {
            for (int i = 0; i < ands.length; i++) {
                String and = ands[i];
                if (and.contains(standardName)) {
                    if (and.contains(")")) {
                        StringBuffer stringBuffer = new StringBuffer();
                        int i1 = and.indexOf(")");
                        String startStr = and.substring(0, i1);
                        String endStr = and.substring(i1);
                        stringBuffer.append(startStr).append(endStr);

                        sb.append(stringBuffer);
                    } else {
                        sb.append(and);
                        for (String name : childNames) {
                            sb.append("/").append(name);
                        }
                    }
                } else {
                    sb.append(and);
                }
                if (i != ands.length - 1) {
                    sb.append("and");
                }
            }

            //删除 标准规则字段
        } else {
            for (int i = 0; i < ands.length; i++) {
                String and = ands[i];
                String[] split1 = field.split(",");
                List<String> list = Arrays.asList(split1);
                if (and.contains(split1[0])) {
                    //包含 此字段 和）
                    if (and.contains(")")) {
                        int i1 = and.indexOf(")");
                        //切分 ）
                        String startStr = and.substring(0, i1);
                        String endStr = and.substring(i1);
                        String[] split = startStr.split("/");
                        if (split.length > 1) {
                            for (int j = 0; j < split.length; j++) {
                                String tempStr = split[j];
                                if (list.contains(tempStr)) {
                                    continue;
                                } else {
                                    sb.append(split[j]);
                                }
                                if (j != split.length - 1) {
                                    sb.append("/");
                                } else {
                                    sb.append(endStr);

                                }
                            }
                        } else {
                            System.out.println("解析错误，不能删除");
                            break;
                        }
//                    stringBuffer.append(startStr).append("/").append(field).append(endStr);
//                    sb.append(stringBuffer);
                    } else {
                        String[] split = and.split("/");
                        if (split.length > 1) {
                            for (int j = 0; j < split.length; j++) {
                                String tempStr = split[j];
                                if (list.contains(tempStr)) {
                                    continue;
                                } else {
                                    sb.append(split[j]);
                                }
                                if (j != split.length - 1) {
                                    sb.append("/");
                                }
                            }
                        } else {
                            System.out.println("解析错误，不能删除");
                            break;
                        }
                    }
                } else {
                    sb.append(and);
                }
                if (i != ands.length - 1) {
                    sb.append("and");
                }

            }
        }
        String s1 = sb.toString();
        String replace = s1.replaceAll("\\/\\)", ")").replaceAll("//", "/").replaceAll("/and", "and").trim();
        Map<String, String> param = new HashMap<>();
        String id = formatrule.getId();
        param.put("_id", id);
        param.put("childElement", replace);
        Object parse = JSONObject.toJSON(param);
        String s = "";
        try {
            s = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.updatechildelement, parse, String.class);
        } catch (Exception e) {
            logger.info("调用" + BaseConstants.updatechildelement + "失败：{}", e.getMessage());
            return false;
        } finally {
            if (StringUtils.isNotBlank(s)) {
                JSONObject jsonObject = JSONObject.parseObject(s);
                String code = jsonObject.getString("code");
                if (BaseConstants.OK.equals(code)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据标准规则查询所有子规则集合
     *
     * @param id
     * @return
     */
    public List<FormatRule> findAllChildRule(String id) {
        List<FormatRule> list = new LinkedList<>();
        Map<String, String> idMap = new HashMap<>();
        idMap.put("_id", id);
        Object obj = JSONObject.toJSON(idMap);
        //获取所有子规则id 或者数据
        String result = null;
        try {

            result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.findallchildrules, obj, String.class);
        } catch (Exception e) {
            logger.debug("调用" + BaseConstants.findallchildrules + "接口失败：{}", e.getMessage());
        } finally {
            if (StringUtils.isNotBlank(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject returnData = (JSONObject) jsonObject.get("result");
                JSONArray decisions = returnData.getJSONArray("decisions");
                Iterator<Object> iterator = decisions.iterator();
                while (iterator.hasNext()) {
                    Map next = (Map) iterator.next();
                    FormatRule formatRule = MapUtil.map2Bean(next, FormatRule.class);
                    list.add(formatRule);
                }

            }

        }
        return list;
    }


    public void deleteChildRuleByCondition(String condition, FormatRule formatRule) {
        String[] split = condition.split(",");
        String ruleCondition = formatRule.getRuleCondition();
        for (String s : split) {
            List<String> ruleConditions = getRuleConditions(ruleCondition);
            if (ruleConditions.contains(s)) {
                String id = formatRule.getId();
                Map<String, String> param = new HashMap<>();
                param.put("_id", id);
                Object obj = JSONObject.toJSON(param);
                try {
                    String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.deleterule, obj, String.class);
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String code = jsonObject.getString("code");
                    if (!BaseConstants.OK.equals(code)) {
                        logger.debug("删除子规则失败失败，子规则id为{}，条件为{}", id, s);
                    }
                } catch (Exception e) {
                    logger.debug("调用" + BaseConstants.deleterule + "接口失败：{}", e.getMessage());
                }
            }

        }
    }
}
