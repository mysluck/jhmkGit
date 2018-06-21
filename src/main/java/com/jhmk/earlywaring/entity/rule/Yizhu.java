package com.jhmk.earlywaring.entity.rule;

public class Yizhu {

    private String order_item_name;//
    private String order_begin_time;//医嘱开始时间
    private String order_end_time;//医嘱结束时间
    private String frequency_name;//每12小时服用一次
    private String frequency_code;//Q12h
    private String dosage_form;//药品剂型
    private String specification;//包装规格说明
    private String drug_amount_value;//药品数量值
    private String order_properties_name;//医嘱性质名称
    private String duration_value;//持续时间

    public String getOrder_item_name() {
        return order_item_name;
    }

    public void setOrder_item_name(String order_item_name) {
        this.order_item_name = order_item_name;
    }

    public String getOrder_begin_time() {
        return order_begin_time;
    }

    public void setOrder_begin_time(String order_begin_time) {
        this.order_begin_time = order_begin_time;
    }

    public String getOrder_end_time() {
        return order_end_time;
    }

    public void setOrder_end_time(String order_end_time) {
        this.order_end_time = order_end_time;
    }

    public String getFrequency_name() {
        return frequency_name;
    }

    public void setFrequency_name(String frequency_name) {
        this.frequency_name = frequency_name;
    }

    public String getFrequency_code() {
        return frequency_code;
    }

    public void setFrequency_code(String frequency_code) {
        this.frequency_code = frequency_code;
    }

    public String getDosage_form() {
        return dosage_form;
    }

    public void setDosage_form(String dosage_form) {
        this.dosage_form = dosage_form;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDrug_amount_value() {
        return drug_amount_value;
    }

    public void setDrug_amount_value(String drug_amount_value) {
        this.drug_amount_value = drug_amount_value;
    }

    public String getOrder_properties_name() {
        return order_properties_name;
    }

    public void setOrder_properties_name(String order_properties_name) {
        this.order_properties_name = order_properties_name;
    }

    public String getDuration_value() {
        return duration_value;
    }

    public void setDuration_value(String duration_value) {
        this.duration_value = duration_value;
    }
}
