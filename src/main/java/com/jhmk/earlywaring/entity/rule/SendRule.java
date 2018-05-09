package com.jhmk.earlywaring.entity.rule;

import java.util.Map;

public class SendRule extends BaseRule {
    private Map<String, String> jianyanbaogao;
    private Map<String, String> jianchabaogao;
    private Map<String, String> yizhu;
    private Map<String, String> binglizhenduan;
    private Map<String, String> shouyezhenduan;

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

    public Map<String, String> getJianyanbaogao() {
        return jianyanbaogao;
    }

    public void setJianyanbaogao(Map<String, String> jianyanbaogao) {
        this.jianyanbaogao = jianyanbaogao;
    }

    public Map<String, String> getJianchabaogao() {
        return jianchabaogao;
    }

    public void setJianchabaogao(Map<String, String> jianchabaogao) {
        this.jianchabaogao = jianchabaogao;
    }

    public Map<String, String> getYizhu() {
        return yizhu;
    }

    public Map<String, String> getBinglizhenduan() {
        return binglizhenduan;
    }

    public void setBinglizhenduan(Map<String, String> binglizhenduan) {
        this.binglizhenduan = binglizhenduan;
    }

    public Map<String, String> getShouyezhenduan() {
        return shouyezhenduan;
    }

    public void setShouyezhenduan(Map<String, String> shouyezhenduan) {
        this.shouyezhenduan = shouyezhenduan;
    }

    public void setYizhu(Map<String, String> yizhu) {


        this.yizhu = yizhu;
    }
}