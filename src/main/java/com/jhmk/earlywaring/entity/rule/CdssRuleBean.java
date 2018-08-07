package com.jhmk.earlywaring.entity.rule;

import java.util.Date;
import java.util.Objects;

public class CdssRuleBean {


    private String id;
    private String doctorId;
    private String createTime;
    private String submitDate;
    private String ruleCondition;
    //tishi
    private String hintContent;
    //释义
    private String signContent;
    //出处
    private String ruleSource;


    private boolean isSubmit=true;//是否提交

    private String progress;//进度

    private String examine;//是否审核

    private String isRun;//是否运行

    private String identification;//专科标识

    private String classification;//规则分类

    private String warninglevel;

    private String sts;//状态 20180522字段新增

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(String ruleCondition) {
        this.ruleCondition = ruleCondition;
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

    public String getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(String ruleSource) {
        this.ruleSource = ruleSource;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getWarninglevel() {
        return warninglevel;
    }

    public void setWarninglevel(String warninglevel) {
        this.warninglevel = warninglevel;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public CdssRuleBean(String medicine, Date date, String ruleCondition, String hintContent, String s, String s1, boolean b, String s2, String 通过, String 是, String s3, String 全科, String 合理用药, String 黄色预警, String a) {
    }

    public CdssRuleBean() {
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        submitDate = submitDate;
    }

    public CdssRuleBean(String doctorId, String createTime, String ruleCondition, String hintContent, String signContent, String ruleSource, boolean isSubmit, String progress, String examine, String isRun, String identification, String classification, String warninglevel, String sts) {
        this.doctorId = doctorId;
        this.createTime = createTime;
        this.ruleCondition = ruleCondition;
        this.hintContent = hintContent;
        this.signContent = signContent;
        this.ruleSource = ruleSource;
        this.isSubmit = isSubmit;
        this.progress = progress;
        this.examine = examine;
        this.isRun = isRun;
        this.identification = identification;
        this.classification = classification;
        this.warninglevel = warninglevel;
        this.sts = sts;
    }


    @Override
    public int hashCode() {

        return Objects.hash(doctorId, createTime, ruleCondition, hintContent, signContent, ruleSource, isSubmit, progress, examine, isRun, identification, classification, warninglevel, sts);
    }
}
