package com.jhmk.earlywaring.entity.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//规则匹配时，获取调用者请求信息 转化为接收规则信息
public class ReciveRule extends BaseRule {

    private List<Map<String, String>> binglizhenduan;
    private List<Map<String, String>> shouyezhenduan;
    private List<Map<String, String>> rjianyanbaogao;
    private List<Map<String, String>> rjianchabaogao;
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

    public List<Map<String, String>> getRjianyanbaogao() {
        return rjianyanbaogao;
    }

    public void setRjianyanbaogao(List<Map<String, String>> rjianyanbaogao) {
        this.rjianyanbaogao = rjianyanbaogao;
    }

    public List<Map<String, String>> getRjianchabaogao() {
        return rjianchabaogao;
    }

    public void setRjianchabaogao(List<Map<String, String>> rjianchabaogao) {
        this.rjianchabaogao = rjianchabaogao;
    }

    @Override
    public String getDept_id() {
        return super.getDept_id();
    }

    @Override
    public void setDept_id(String dept_id) {
        super.setDept_id(dept_id);
    }

    @Override
    public String getDoctor_id() {
        return super.getDoctor_id();
    }

    @Override
    public void setDoctor_id(String doctor_id) {
        super.setDoctor_id(doctor_id);
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


    public List<Map<String, String>> getYizhu() {
        return yizhu;
    }

    public void setYizhu(List<Map<String, String>> yizhu) {
        this.yizhu = yizhu;
    }


    public static ReciveRule fill(JSONObject jo) {
        ReciveRule o = new ReciveRule();

        if (jo.containsKey("doctor_id")) {
            o.setPatient_id(jo.getString("doctor_id"));
        }
        if (jo.containsKey("dept_id")) {
            o.setPatient_id(jo.getString("dept_id"));
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
            o.setBinglizhenduan((List<Map<String, String>>) jo.get("binglizhenduan"));
        }
        if (jo.containsKey("shouyezhenduan")) {
            o.setShouyezhenduan((List<Map<String, String>>) jo.get("shouyezhenduan"));
        }
        if (jo.containsKey("ruyuanjilu")) {
            o.setRuyuanjilu((Map<String, String>) jo.get("ruyuanjilu"));
        }
        if (jo.containsKey("physicalSign")) {
            o.setPhysicalSign((Map<String, String>) jo.get("physicalSign"));
        }
        if (jo.containsKey("jianyanbaogao")) {
            o.setRjianyanbaogao((List<Map<String, String>>) jo.get("jianyanbaogao"));
        }
        if (jo.containsKey("jianchabaogao")) {
            o.setRjianchabaogao((List<Map<String, String>>) jo.get("jianchabaogao"));
        }
        if (jo.containsKey("yizhu")) {
            o.setYizhu((List<Map<String, String>>) jo.get("yizhu"));
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
