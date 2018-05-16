package com.jhmk.earlywaring.entity.rule;

import java.util.Map;

public class BaseRule {
    private String dept_id;
    private String doctor_id;
    private String patient_id;
    private String visit_id;
    private String pageSource;

    private Map<String,String> binganshouye;
    private Map<String,String> bingchengjilu;
    private Map<String,String> ruyuanjilu;
    private Map<String,String> physicalSign;

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
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

    public Map<String, String> getRuyuanjilu() {
        return ruyuanjilu;
    }

    public void setRuyuanjilu(Map<String, String> ruyuanjilu) {
        this.ruyuanjilu = ruyuanjilu;
    }

    public Map<String, String> getPhysicalSign() {
        return physicalSign;
    }

    public void setPhysicalSign(Map<String, String> physicalSign) {
        this.physicalSign = physicalSign;
    }

    @Override
    public String toString() {
        return "BaseRule{" +
                "patient_id='" + patient_id + '\'' +
                ", visit_id='" + visit_id + '\'' +
                ", binganshouye=" + binganshouye +
                ", bingchengjilu=" + bingchengjilu +
                ", ruyuanjilu=" + ruyuanjilu +
                ", physicalSign=" + physicalSign +
                '}';
    }
}
