package com.jhmk.earlywaring.webservice.entity;

import java.util.List;

/**
 * @author ziyu.zhou
 * @date 2018/7/23 14:55
 */

public class OriginalJianyanbaogao {
    //检验项
    private String lab_item_name;
    //检验样本
    private String specimen;
    //检验结果产生时间
    private String report_time;
    //检验号
    private String report_no;
    //检验结果列表
    private List<JianyanbaogaoForAuxiliary> labTestItems;

    public String getLab_item_name() {
        return lab_item_name;
    }

    public void setLab_item_name(String lab_item_name) {
        this.lab_item_name = lab_item_name;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public List<JianyanbaogaoForAuxiliary> getLabTestItems() {
        return labTestItems;
    }

    public void setLabTestItems(List<JianyanbaogaoForAuxiliary> labTestItems) {
        this.labTestItems = labTestItems;
    }
}
