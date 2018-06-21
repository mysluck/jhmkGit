package com.jhmk.earlywaring.entity.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.util.MapUtil;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

public class BaseRule {
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
    private List<Map<String, String>> binglizhenduan;
    private List<Map<String, String>> shouyezhenduan;
    private List<Map<String, String>> jianyanbaogao;
    private List<Map<String, String>> jianchabaogao;
    private List<Map<String, String>> yizhu;

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

    public Map<String, String> getBinganshouye() {
        return binganshouye;
    }

    public void setBinganshouye(Map<String, String> binganshouye) {
        this.binganshouye = binganshouye;
    }

    public Map<String, String> getBingchengjilu() {
        return bingchengjilu;
    }

    public void setBingchengjilu(Map<String, String> bingchengjilu) {
        this.bingchengjilu = bingchengjilu;
    }

    public List<Map<String, String>> getBinglizhenduan() {
        return binglizhenduan;
    }

    public void setBinglizhenduan(List<Map<String, String>> binglizhenduan) {
        this.binglizhenduan = binglizhenduan;
    }

    public List<Map<String, String>> getShouyezhenduan() {
        return shouyezhenduan;
    }

    public void setShouyezhenduan(List<Map<String, String>> shouyezhenduan) {
        this.shouyezhenduan = shouyezhenduan;
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

    public List<Map<String, String>> getYizhu() {
        return yizhu;
    }

    public void setYizhu(List<Map<String, String>> yizhu) {
        this.yizhu = yizhu;
    }

    public static BaseRule fill(JSONObject jo) {
        BaseRule o = new BaseRule();

        if (jo.containsKey("doctor_id")) {
            o.setDoctor_id(jo.getString("doctor_id"));
        }
        if (jo.containsKey("doctor_name")) {
            o.setDoctor_name(jo.getString("doctor_name"));
        }
        if (jo.containsKey("dept_code")) {
            o.setDept_code(jo.getString("dept_code"));
        }
        if (jo.containsKey("warnSource")) {
            o.setWarnSource(jo.getString("warnSource"));
        }
        if (jo.containsKey("patient_id")) {
            o.setPatient_id(jo.getString("patient_id"));
        }
        if (jo.containsKey("visit_id")) {
            o.setVisit_id(jo.getString("visit_id"));
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
        if (jo.containsKey("binglizhenduan")) {
            o.setShouyezhenduan((List<Map<String, String>>) jo.get("binglizhenduan"));
        }
        if (jo.containsKey("shouyezhenduan")) {
            o.setShouyezhenduan((List<Map<String, String>>) jo.get("shouyezhenduan"));
        }
        if (jo.containsKey("ruyuanjilu")) {
            o.setRuyuanjilu((Map<String, String>) jo.get("ruyuanjilu"));
        }

        if (jo.containsKey("jianyanbaogao")) {
            List<Map<String, String>> jianyanbaogao = (List<Map<String, String>>) jo.get("jianyanbaogao");
            List<Map<String, String>> maps = deleteRepetitionFieldByMap(jianyanbaogao, "lab_sub_item_name", "report_time");
            o.setJianyanbaogao(maps);
        }
        if (jo.containsKey("jianchabaogao")) {
            List<Map<String, String>> jianchabaogao = (List<Map<String, String>>) jo.get("jianchabaogao");
            List<Map<String, String>> maps = deleteRepetitionFieldByMap(jianchabaogao, "exam_item_name", "exam_time");
            o.setJianchabaogao(maps);
        }
        if (jo.containsKey("yizhu")) {
            List<Yizhu> beanList = new ArrayList<>();
            List<Map<String, String>> yizhus = (List<Map<String, String>>) jo.get("yizhu");
            List<Map<String, String>> tempMaps = deleteRepetitionFieldByMap(yizhus, "order_item_name", "order_begin_time");
            o.setYizhu(tempMaps);
        }
        return o;
    }


    /**
     * //去除重复字段 保留时间最大的
     *
     * @param list   对象
     * @param field1 去除重复字段
     * @param field2 判断字段大小
     * @return
     */
    private static List<Map<String, String>> deleteRepetitionField(List<Map<String, String>> list, String field1, String field2) {
        System.out.println("开始：=========="+list.size());
        for (int i = 0; i < list.size(); i++) {
            Map m1 = list.get(i);

            for (int j = i + 1; j < list.size(); j++) {
                Map m2 = list.get(j);
                if (m1.get(field1).equals(m2.get(field1))) {
                    Object time1 = m1.get(field2);
                    Object time2 = m2.get(field2);
                    Date date1 = DateFormatUtil.parseDate(time1.toString(), DateFormatUtil.DATETIME_PATTERN_SS);
                    Date date2 = DateFormatUtil.parseDate(time2.toString(), DateFormatUtil.DATETIME_PATTERN_SS);
                    if (date1.getTime() > date2.getTime()) {
                        list.remove(j);
                    }else {
                        list.remove(i);
                    }
                    continue;
                }
            }
        }
        System.out.println("结束：=========="+list.size());

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
}
