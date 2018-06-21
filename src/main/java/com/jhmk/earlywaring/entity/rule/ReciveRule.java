package com.jhmk.earlywaring.entity.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.MapUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.*;

//规则匹配时，获取调用者请求信息 转化为接收规则信息
public class ReciveRule {
    @Autowired
    RestTemplate restTemplate;
    private String doctor_id;
    private String doctor_name;
    private String dept_code;
    private String warnSource;
    private String patient_id;
    private String visit_id;
    private String pageSource;

    private Map<String, String> bingchengjilu;
    private Map<String, String> binganshouye;
    private Map<String, String> ruyuanjilu;
    private List<Map<String, String>> jianyanbaogao;
    private List<Map<String, String>> jianchabaogao;
    private List<Zhenduan> binglizhenduan;
    private List<Zhenduan> shouyezhenduan;
    private List<Yizhu> yizhu;



    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getWarnSource() {
        return warnSource;
    }

    public void setWarnSource(String warnSource) {
        this.warnSource = warnSource;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public String getPageSource() {
        return pageSource;
    }

    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

    public Map<String, String> getBingchengjilu() {
        return bingchengjilu;
    }

    public void setBingchengjilu(Map<String, String> bingchengjilu) {
        this.bingchengjilu = bingchengjilu;
    }

    public Map<String, String> getBinganshouye() {
        return binganshouye;
    }

    public void setBinganshouye(Map<String, String> binganshouye) {
        this.binganshouye = binganshouye;
    }

    public Map<String, String> getRuyuanjilu() {
        return ruyuanjilu;
    }

    public void setRuyuanjilu(Map<String, String> ruyuanjilu) {
        this.ruyuanjilu = ruyuanjilu;
    }

    public List<Map<String, String>> getJianyanbaogao() {
        return jianyanbaogao;
    }

    public void setJianyanbaogao(List<Map<String, String>> jianyanbaogao) {
        this.jianyanbaogao = jianyanbaogao;
    }

    public List<Map<String, String>> getJianchabaogao() {
        return jianchabaogao;
    }

    public void setJianchabaogao(List<Map<String, String>> jianchabaogao) {
        this.jianchabaogao = jianchabaogao;
    }

    public List<Zhenduan> getBinglizhenduan() {
        return binglizhenduan;
    }

    public void setBinglizhenduan(List<Zhenduan> binglizhenduan) {
        this.binglizhenduan = binglizhenduan;
    }

    public List<Zhenduan> getShouyezhenduan() {
        return shouyezhenduan;
    }

    public void setShouyezhenduan(List<Zhenduan> shouyezhenduan) {
        this.shouyezhenduan = shouyezhenduan;
    }

    public List<Yizhu> getYizhu() {
        return yizhu;
    }

    public void setYizhu(List<Yizhu> yizhu) {
        this.yizhu = yizhu;
    }

    public static ReciveRule fill(JSONObject jo) {
        ReciveRule o = new ReciveRule();

        if (jo.containsKey("doctor_id")) {
            o.setDoctor_id(jo.getString("doctor_id"));
        }
        if (jo.containsKey("doctor_name")) {
            o.setDoctor_name(jo.getString("doctor_name"));
        }
        if (jo.containsKey("dept_code")) {
            o.setDept_code(jo.getString("dept_code"));
        }
        if (jo.containsKey("patient_id")) {
            o.setPatient_id(jo.getString("patient_id"));
        }
        if (jo.containsKey("visit_id")) {
            o.setVisit_id(jo.getString("visit_id"));
        }
        if (jo.containsKey("warnSource")) {
            o.setWarnSource(jo.getString("warnSource"));
        }
        if (jo.containsKey("pageSource")) {
            o.setPageSource(jo.getString("pageSource"));
        }
        if (jo.containsKey("binganshouye")) {

            o.setBinganshouye((Map<String, String>) jo.get("binganshouye"));
        }
        if (jo.containsKey("bingchengjilu")) {
            o.setBingchengjilu((Map<String, String>) jo.get("bingchengjilu"));
        }

        if (jo.containsKey("ruyuanjilu")) {
            o.setRuyuanjilu((Map<String, String>) jo.get("ruyuanjilu"));
        }
        if (jo.containsKey("jianyanbaogao")) {
            List<Map<String, String>> jianyanbaogao = (List<Map<String, String>>) jo.get("jianyanbaogao");
            List<Map<String, String>> maps = deleteRepetitionField(jianyanbaogao, "lab_sub_item_name", "report_time");
            o.setJianyanbaogao(maps);
        }
        if (jo.containsKey("jianchabaogao")) {
            List<Map<String, String>> jianchabaogao = (List<Map<String, String>>) jo.get("jianchabaogao");
            List<Map<String, String>> maps = deleteRepetitionField(jianchabaogao, "exam_item_name", "exam_time");
            o.setJianchabaogao(maps);
        }
        if (jo.containsKey("binglizhenduan")) {
            List<Zhenduan> beanList = new ArrayList<>();
            List<Map<String, String>> binglizhenduans = (List<Map<String, String>>) jo.get("binglizhenduan");
            for (Map<String, String> m : binglizhenduans) {
                Zhenduan bean = MapUtil.map2Bean(m, Zhenduan.class);
                beanList.add(bean);
            }
            o.setBinglizhenduan(beanList);
        }
        if (jo.containsKey("shouyezhenduan")) {
            List<Zhenduan> beanList = new ArrayList<>();
            List<Map<String, String>> shouyezhenduans = (List<Map<String, String>>) jo.get("shouyezhenduan");
            for (Map<String, String> m : shouyezhenduans) {
                Zhenduan bean = MapUtil.map2Bean(m, Zhenduan.class);
                beanList.add(bean);
            }
            o.setShouyezhenduan(beanList);
        }
        if (jo.containsKey("yizhu")) {
            List<Yizhu> beanList = new ArrayList<>();
            List<Map<String, String>> yizhus = (List<Map<String, String>>) jo.get("yizhu");
            List<Map<String, String>> tempMaps = deleteRepetitionFieldByMap(yizhus, "order_item_name", "order_begin_time");


            for (Map<String, String> m : tempMaps) {
                Yizhu bean = MapUtil.map2Bean(m, Yizhu.class);
                beanList.add(bean);
            }
            o.setYizhu(beanList);
        }
        return o;
    }

    public static void main(String[] args) {
        String s = "2017-05-02 11:12:12";
        String s1 = "2017-05-02 10:12:12";
        System.out.println(s.compareTo(s1));
    }

    /**
     * //去除重复字段 保留时间最大的
     *
     * @param list   对象
     * @param field1 去除重复字段
     * @param field2 判断字段大小
     * @return
     */
    private static List<Map<String, String>> deleteRepetitionField(List<Map<String, String>> list, String
            field1, String field2) {
        for (int i = 0; i < list.size(); i++) {
            Map m1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Map m2 = list.get(j);
                if (m1.get(field1).equals(m2.get(field1))) {
                    Object time1 = m1.get(field2);
                    Object time2 = m2.get(field2);
                    if (time1.toString().compareTo(time2.toString()) > 1) {
                        list.remove(j);
                    } else {
                        list.remove(i);
                    }
                    continue;
                }
            }
        }

        return list;
    }

    private static List<Map<String, String>> deleteRepetitionFieldByMap(List<Map<String, String>> list, String
            field1, String field2) {
        Map<String, Map<String, String>> tempMap = new HashedMap();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);
            String fName = map.get(field1);
            if (tempMap.containsKey(fName)) {
                //已存map
                Map<String, String> tmap = tempMap.get(fName);
                if (tmap.get(field2).compareTo(map.get(field2)) == -1) {
                    tempMap.put(fName, map);
                }
            } else {
                tempMap.put(fName, map);
            }
        }
        List<Map<String, String>> endList = new ArrayList<>();
        Iterator<Map.Entry<String, Map<String, String>>> iterator = tempMap.entrySet().iterator();
        while (iterator.hasNext()) {
            endList.add(iterator.next().getValue());
        }
        return endList;
    }

    /**
     * @param jo
     * @param field1 一级字段名
     * @param field2 2级字段名
     * @param value  修改的值
     */
    public static List<Map<String, String>> updataFieldValue(JSONObject jo, String field1, String field2, String
            value) {
        List<Map<String, String>> resultMap = new ArrayList<>();
        List<Map<String, String>> shouyezhenduan = (List<Map<String, String>>) jo.get(field1);
        Iterator<Map<String, String>> iterator = shouyezhenduan.iterator();
        while (iterator.hasNext()) {
            Map<String, String> next = iterator.next();
            next.put(field2, value);
            resultMap.add(next);
        }
        return resultMap;
    }

    public static List<ReciveRule> fillList(JSONArray ja) {
        if (ja == null || ja.size() == 0)
            return null;
        List<ReciveRule> sqs = new ArrayList<ReciveRule>();
        for (int i = 0; i < ja.size(); i++) {
            sqs.add(fill(ja.getJSONObject(i)));
        }
        return sqs;
    }
}
