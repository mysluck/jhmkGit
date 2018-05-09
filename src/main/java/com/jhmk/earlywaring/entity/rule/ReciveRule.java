package com.jhmk.earlywaring.entity.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReciveRule extends BaseRule {


    private List<Map<String, String>>  binglizhenduan;
    private List<Map<String, String>>  shouyezhenduan;
    private List<Map<String, String>>  jianyanbaogao;
    private List<Map<String, String>> jianchabaogao;
    private List<Map<String, String>> yizhu;

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

    public void setJianyanbaogao(List<Map<String, String>> jianyanbaogao) {
        this.jianyanbaogao = jianyanbaogao;
    }

    @Override
    public String getPatient_id() {
        return super.getPatient_id();
    }

    @Override
    public void setPatient_id(String patient_id) {
        super.setPatient_id(patient_id);
    }

    @Override
    public String getVisit_id() {
        return super.getVisit_id();
    }

    @Override
    public void setVisit_id(String visit_id) {
        super.setVisit_id(visit_id);
    }

    @Override
    public String getPageSource() {
        return super.getPageSource();
    }

    @Override
    public void setPageSource(String pageSource) {
        super.setPageSource(pageSource);
    }

    @Override
    public Map<String, String> getBinganshouye() {
        return super.getBinganshouye();
    }

    @Override
    public void setBinganshouye(Map<String, String> binganshouye) {
        super.setBinganshouye(binganshouye);
    }

    @Override
    public Map<String, String> getBingchengjilu() {
        return super.getBingchengjilu();
    }

    @Override
    public void setBingchengjilu(Map<String, String> bingchengjilu) {
        super.setBingchengjilu(bingchengjilu);
    }

    @Override
    public Map<String, String> getRuyuanjilu() {
        return super.getRuyuanjilu();
    }

    @Override
    public void setRuyuanjilu(Map<String, String> ruyuanjilu) {
        super.setRuyuanjilu(ruyuanjilu);
    }

    @Override
    public Map<String, String> getPhysicalSign() {
        return super.getPhysicalSign();
    }

    @Override
    public void setPhysicalSign(Map<String, String> physicalSign) {
        super.setPhysicalSign(physicalSign);
    }

    public List<Map<String, String>> getJianyanbaogao() {
        return jianyanbaogao;
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

    public static ReciveRule fill(JSONObject jo) {
        ReciveRule o = new ReciveRule();
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
            o.setBingchengjilu((Map<String, String>)jo.get("bingchengjilu"));
        }
        if (jo.containsKey("binglizhenduan")) {
            o.setBinglizhenduan((List<Map<String, String>>)jo.get("binglizhenduan"));
        }
        if (jo.containsKey("shouyezhenduan")) {
            o.setShouyezhenduan((List<Map<String, String>>)jo.get("shouyezhenduan"));
        }
        if (jo.containsKey("ruyuanjilu")) {
            o.setRuyuanjilu((Map<String, String>)jo.get("ruyuanjilu"));
        }
        if (jo.containsKey("physicalSign")) {
            o.setPhysicalSign((Map<String, String>)jo.get("physicalSign"));
        }
        if (jo.containsKey("jianyanbaogao")) {
            o.setJianyanbaogao((List<Map<String, String>>)jo.get("jianyanbaogao"));
        }
        if (jo.containsKey("jianchabaogao")) {
            o.setJianchabaogao((List<Map<String, String>>)jo.get("jianchabaogao"));
        }
        if (jo.containsKey("yizhu")) {
            o.setYizhu((List<Map<String, String>>)jo.get("yizhu"));
        }
        return o;
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
