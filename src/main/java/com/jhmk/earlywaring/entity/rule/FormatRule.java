package com.jhmk.earlywaring.entity.rule;

import java.io.Serializable;
import java.io.StringReader;

/**
 * 格式化规则 用于前台显示
 */
public class FormatRule implements Serializable {
    private String doctorId;
    private String createTime;
    //规则条件
    private String ruleCondition;
    private String id;
    //是否提交
    private String isSubmit;
    //规则出处
    private String ruleSource;
    //所属类别 医嘱 、诊断
    private String classification;
    //提示内容
    private String hintContent;
    //示意内容
    private String signContent;
    //预警等级
    private String warninglevel;
    //审核
    private String examine;
    //是否运行规则
    private String isRun;

    //专科标志
    private String identification;

    public FormatRule() {
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(String ruleCondition) {
        this.ruleCondition = ruleCondition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(String ruleSource) {
        this.ruleSource = ruleSource;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getHintContent() {
        return hintContent;
    }

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }

    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    public String getWarninglevel() {
        return warninglevel;
    }

    public void setWarninglevel(String warninglevel) {
        this.warninglevel = warninglevel;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getIsRun() {
        return isRun;
    }

    public void setIsRun(String isRun) {
        this.isRun = isRun;
    }
}
