package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmShowLog;
import com.jhmk.earlywaring.entity.rule.CdssRuleBean;
import com.jhmk.earlywaring.entity.rule.LogMapping;
import com.jhmk.earlywaring.entity.rule.StandardRule;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.MapUtil;
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
public class ResolveRuleService {

    private static final String symbol = "[]";


    /**
     * 获取cdss规则解析
     * 用于标准规则 获取提示释义等信息
     */
    public CdssRuleBean getStandardRule(String ruleStr) {
        JSONObject jObject = JSON.parseObject(ruleStr);
        Object result = jObject.get("result");
        CdssRuleBean cdssRuleBean = null;
        if (ObjectUtils.anyNotNull(result) && !symbol.equals(result)) {
            Map resultMap = (Map) result;
            cdssRuleBean = MapUtil.map2Bean(resultMap, CdssRuleBean.class);
        }
        return cdssRuleBean;
    }


    //获取大并列 条件信息
    public void anaOldRule(String str) {
        List<List<StandardRule>> basicStandardRule = getBasicStandardRule(str);
        String string = JSONObject.toJSONString(basicStandardRule);
        System.out.println(string);
    }




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
    private List<StandardRule> getOneRule(String str) {
        List<StandardRule> list = new LinkedList<>();
        String s = str.replaceAll("\\(", "").replaceAll("\\)", "");
        String[] ands = s.split("and");
        for (String oneStr : ands) {
            StandardRule rule = anaSyspol(oneStr);
            list.add(rule);
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "123";
        String[] ands = s.split("and");
        for (String s1 : ands) {
            System.out.println(s1);
        }
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
            rule.setAllValues(Arrays.asList(split[1].split("/")));        }
        return rule;
    }
}
