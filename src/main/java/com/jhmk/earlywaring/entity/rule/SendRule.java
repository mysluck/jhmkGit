package com.jhmk.earlywaring.entity.rule;

import java.util.List;
import java.util.Map;

public class SendRule extends BaseRule {
    private List<Map<String, String>> jianyanbaogao;
    private List<Map<String, String>> jianchabaogao;
    private Map<String, String> yizhu;
    private List<Map<String, String>> binglizhenduan;
    private List<Map<String, String>> shouyezhenduan;

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

    public Map<String, String> getYizhu() {
        return yizhu;
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

    public void setYizhu(Map<String, String> yizhu) {


        this.yizhu = yizhu;
    }
}