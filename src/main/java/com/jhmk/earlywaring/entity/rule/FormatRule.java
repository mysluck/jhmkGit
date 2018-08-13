package com.jhmk.earlywaring.entity.rule;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;
import java.util.Objects;

/**
 * 格式化规则 用于前台显示
 */
public class FormatRule implements Serializable {

    private String id;
    private String doctorId;
    private String createTime;
    private String submitDate;
    //规则条件
    private String ruleCondition;
    //标准规则专用 标志子元素
    private String childElement;
    //tishi
    private String hintContent;
    //释义
    private String signContent;
    //出处
    private String ruleSource;


    private boolean isSubmit;//是否提交

    private String progress;//进度

    private String examine;//是否审核

    private String isRun;//是否运行

    private String identification;//专科标识

    private String classification;//规则分类

    private String warninglevel;
    //是否是标准规则
    private String isStandard;
    //父id
    private String  parentId;

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

    public boolean getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(boolean submit) {
        this.isSubmit = submit;
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

    public String getChildElement() {
        return childElement;
    }

    public void setChildElement(String childElement) {
        this.childElement = childElement;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }



    public FormatRule() {
    }

    public String getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(String standard) {
        isStandard = standard;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
