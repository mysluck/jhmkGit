package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.rule.ReciveRule;
import com.jhmk.earlywaring.entity.rule.SendRule;
import com.jhmk.earlywaring.util.CompareUtil;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.springframework.beans.BeanUtils;
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
    public Object ruleMatch(String mes) {
        JSONObject object = JSON.parseObject(mes);
        ReciveRule fill = ReciveRule.fill(object);
        SendRule sendRule = new SendRule();
        BeanUtils.copyProperties(fill, sendRule);
        System.out.println(sendRule.toString());
        List<Map<String, String>> binglizhenduan = fill.getBinglizhenduan();
        Collections.sort(binglizhenduan,  CompareUtil.createComparatorForMap(-1, "diagnosis_time"));
        sendRule.setBinglizhenduan(binglizhenduan.get(0));
        List<Map<String, String>> shouyezhenduan = fill.getShouyezhenduan();
        Collections.sort(shouyezhenduan, CompareUtil.createComparatorForMap(-1, "diagnosis_time"));
        sendRule.setShouyezhenduan(shouyezhenduan.get(0));
        //检查报告
        List<Map<String, String>> jianchabaogao = fill.getJianchabaogao();
        Collections.sort(jianchabaogao, CompareUtil.createComparatorForMap(-1, "exam_time"));
        sendRule.setJianchabaogao(jianchabaogao.get(0));
        //检验报告
        List<Map<String, String>> jianyanbaogao = fill.getJianyanbaogao();
        Collections.sort(jianyanbaogao, CompareUtil.createComparatorForMap(-1, "report_time"));
        sendRule.setJianchabaogao(jianyanbaogao.get(0));
        return JSON.toJSON(sendRule);
    }

}
