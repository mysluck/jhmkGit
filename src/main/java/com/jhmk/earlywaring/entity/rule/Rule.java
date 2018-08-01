package com.jhmk.earlywaring.entity.rule;

import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.util.MapUtil;
import com.jhmk.earlywaring.webservice.entity.OriginalJianyanbaogao;

import java.io.Serializable;
import java.util.*;

/**
 * @author ziyu.zhou
 * @date 2018/7/24 13:55
 */

public class Rule implements Serializable {

    private String doctor_id;
    private String doctor_name;
    private String dept_code;
    private String warnSource;
    private String patient_id;
    private String visit_id;
    private String pageSource;
    //入院时间
    private String admission_time;
    //出院时间
    private String discharge_time;

    private Bingchengjilu bingchengjilu;
    private Binganshouye binganshouye;
    private Ruyuanjilu ruyuanjilu;
    private List<Jianyanbaogao> jianyanbaogao;
    private List<OriginalJianyanbaogao> originalJianyanbaogaos;
    private List<Jianchabaogao> jianchabaogao;
    private List<Binglizhenduan> binglizhenduan;
    private List<Shouyezhenduan> shouyezhenduan;
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

    public String getAdmission_time() {
        return admission_time;
    }

    public void setAdmission_time(String admission_time) {
        this.admission_time = admission_time;
    }

    public String getDischarge_time() {
        return discharge_time;
    }

    public void setDischarge_time(String discharge_time) {
        this.discharge_time = discharge_time;
    }

    public Bingchengjilu getBingchengjilu() {
        return bingchengjilu;
    }

    public void setBingchengjilu(Bingchengjilu bingchengjilu) {
        this.bingchengjilu = bingchengjilu;
    }

    public Binganshouye getBinganshouye() {
        return binganshouye;
    }

    public void setBinganshouye(Binganshouye binganshouye) {
        this.binganshouye = binganshouye;
    }

    public Ruyuanjilu getRuyuanjilu() {
        return ruyuanjilu;
    }

    public void setRuyuanjilu(Ruyuanjilu ruyuanjilu) {
        this.ruyuanjilu = ruyuanjilu;
    }

    public List<Jianyanbaogao> getJianyanbaogao() {
        return jianyanbaogao;
    }

    public void setJianyanbaogao(List<Jianyanbaogao> jianyanbaogao) {
        this.jianyanbaogao = jianyanbaogao;
    }

    public List<Jianchabaogao> getJianchabaogao() {
        return jianchabaogao;
    }

    public void setJianchabaogao(List<Jianchabaogao> jianchabaogao) {
        this.jianchabaogao = jianchabaogao;
    }

    public List<Binglizhenduan> getBinglizhenduan() {
        return binglizhenduan;
    }

    public void setBinglizhenduan(List<Binglizhenduan> binglizhenduan) {
        this.binglizhenduan = binglizhenduan;
    }

    public List<Shouyezhenduan> getShouyezhenduan() {
        return shouyezhenduan;
    }

    public void setShouyezhenduan(List<Shouyezhenduan> shouyezhenduan) {
        this.shouyezhenduan = shouyezhenduan;
    }

    public List<Yizhu> getYizhu() {
        return yizhu;
    }

    public void setYizhu(List<Yizhu> yizhu) {
        this.yizhu = yizhu;
    }

    public List<OriginalJianyanbaogao> getOriginalJianyanbaogaos() {
        return originalJianyanbaogaos;
    }

    public void setOriginalJianyanbaogaos(List<OriginalJianyanbaogao> originalJianyanbaogaos) {
        this.originalJianyanbaogaos = originalJianyanbaogaos;
    }

    public static Rule fill(JSONObject jo) {
        Rule o = new Rule();

        if (jo.containsKey("doctor_id")) {
            o.setDoctor_id(jo.getString("doctor_id"));
        }
        if (jo.containsKey("doctor_name")) {
            o.setDoctor_name(jo.getString("doctor_name"));
        }
        if (jo.containsKey("patient_id")) {
            o.setPatient_id(jo.getString("patient_id"));
        }
        if (jo.containsKey("visit_id")) {
            o.setVisit_id(jo.getString("visit_id"));
        }

        if (jo.containsKey("discharge_time")) {
            o.setDischarge_time(jo.getString("discharge_time"));
        }
        if (jo.containsKey("admission_time")) {
            o.setAdmission_time(jo.getString("admission_time"));
        }

        if (jo.containsKey("dept_code")) {
            o.setDept_code(jo.getString("dept_code"));
        }

        if (jo.containsKey("warnSource")) {
            o.setWarnSource(jo.getString("warnSource"));
        }
        if (jo.containsKey("pageSource")) {
            o.setPageSource(jo.getString("pageSource"));
        }
        if (jo.containsKey("binganshouye")) {
            Map<String, String> map = (Map<String, String>) jo.get("binganshouye");
            map.put("patient_id",jo.getString("patient_id"));
            map.put("visit_id",jo.getString("visit_id"));
            Binganshouye binganshouye = MapUtil.map2Bean(map, Binganshouye.class);
            o.setBinganshouye(binganshouye);
        }
        if (jo.containsKey("bingchengjilu")) {
            Map<String, String> map = (Map<String, String>) jo.get("bingchengjilu");
            map.put("patient_id",jo.getString("patient_id"));
            map.put("visit_id",jo.getString("visit_id"));
            Bingchengjilu bingchengjilu = MapUtil.map2Bean(map, Bingchengjilu.class);

            o.setBingchengjilu(bingchengjilu);
        }

        if (jo.containsKey("ruyuanjilu")) {
            Map<String, String> map = (Map<String, String>) jo.get("ruyuanjilu");
            map.put("patient_id",jo.getString("patient_id"));
            map.put("visit_id",jo.getString("visit_id"));
            Ruyuanjilu ruyuanjilu = MapUtil.map2Bean(map, Ruyuanjilu.class);
            o.setRuyuanjilu(ruyuanjilu);
        }
        if (jo.containsKey("binglizhenduan")) {
            List<Binglizhenduan> beanList = new ArrayList<>();
            List<Map<String, String>> binglizhenduans = (List<Map<String, String>>) jo.get("binglizhenduan");
            for (Map<String, String> m : binglizhenduans) {
                m.put("patient_id",jo.getString("patient_id"));
                m.put("visit_id",jo.getString("visit_id"));
                Binglizhenduan bean = MapUtil.map2Bean(m, Binglizhenduan.class);
                beanList.add(bean);
            }
            o.setBinglizhenduan(beanList);
        }
        if (jo.containsKey("shouyezhenduan")) {
            List<Shouyezhenduan> beanList = new ArrayList<>();
            List<Map<String, String>> shouyezhenduans = (List<Map<String, String>>) jo.get("shouyezhenduan");
            for (Map<String, String> m : shouyezhenduans) {
                m.put("patient_id",jo.getString("patient_id"));
                m.put("visit_id",jo.getString("visit_id"));
                Shouyezhenduan bean = MapUtil.map2Bean(m, Shouyezhenduan.class);
                beanList.add(bean);
            }
            o.setShouyezhenduan(beanList);
        }
        if (jo.containsKey("yizhu")) {
            List<Yizhu> beanList = new ArrayList<>();
            List<Map<String, String>> yizhus = (List<Map<String, String>>) jo.get("yizhu");
            for (Map<String, String> m : yizhus) {
                m.put("patient_id",jo.getString("patient_id"));
                m.put("visit_id",jo.getString("visit_id"));
                Yizhu bean = MapUtil.map2Bean(m, Yizhu.class);
                beanList.add(bean);
            }
            o.setYizhu(beanList);
        }

        if (jo.containsKey("jianyanbaogao")) {
            List<Map<String, String>> jianyanbaogao = (List<Map<String, String>>) jo.get("jianyanbaogao");
            List<Jianyanbaogao> beanList = new LinkedList<>();
            for (Map<String, String> m : jianyanbaogao) {
                Jianyanbaogao bean = MapUtil.map2Bean(m, Jianyanbaogao.class);
                beanList.add(bean);
            }
            o.setJianyanbaogao(beanList);
        }
        if (jo.containsKey("jianchabaogao")) {
            List<Map<String, String>> jianchabaogao = (List<Map<String, String>>) jo.get("jianchabaogao");
            List<Jianchabaogao> beanList = new LinkedList<>();
            for (Map<String, String> m : jianchabaogao) {
                Jianchabaogao bean = MapUtil.map2Bean(m, Jianchabaogao.class);
                beanList.add(bean);
            }
            o.setJianchabaogao(beanList);
        }

        return o;
    }

}

