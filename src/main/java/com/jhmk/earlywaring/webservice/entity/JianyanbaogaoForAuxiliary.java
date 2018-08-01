package com.jhmk.earlywaring.webservice.entity;

/**
 * @author ziyu.zhou
 * @date 2018/7/23 15:00
 */

import java.io.Serializable;

/**
 * 检验报告明细
 */
public class JianyanbaogaoForAuxiliary implements Serializable{

    private String name;//检验细项名称
    private String lab_qual_result;//定性结果
    private String lab_result;//检验定量结果值
    private String unit;//检验定量结果单位
    private String result_status_code;//检验定量结果变化 正常：N    偏高：H   偏低：L
    private String reference_range; //参考区间
    private String report_no; //和检验主表关系字段

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLab_qual_result() {
        return lab_qual_result;
    }

    public void setLab_qual_result(String lab_qual_result) {
        this.lab_qual_result = lab_qual_result;
    }

    public String getLab_result() {
        return lab_result;
    }

    public void setLab_result(String lab_result) {
        this.lab_result = lab_result;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getResult_status_code() {
        return result_status_code;
    }

    public void setResult_status_code(String result_status_code) {
        this.result_status_code = result_status_code;
    }

    public String getReference_range() {
        return reference_range;
    }

    public void setReference_range(String reference_range) {
        this.reference_range = reference_range;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }
}
