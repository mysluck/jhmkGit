package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "sm_hosptail_log", schema = "jhmk_waring", catalog = "")
public class SmHosptailLog {
    private int id;
    private String suffererId;
    private String doctorId;
    private String deptId;
    private String fa_dept_name;
    private String faDeptName;
    private String hitRate1;
    private String hitRate3;
    private String hitRate5;
    //确认主疾病
    private String affirmSickness;
    //疾病等级
    private String sicknessGrade;



    //警报原因
    private String alarmCause;
    //警报触发条件
    private String alarmCondition;
    //警报结果
    private String alarmResult;
    //1-科室预警 2-住院预警
    private String alarmCode;
    //0 触发警报 1 未触发
    private String alarmStatus;
    //c commom 通用 s Specialty 专科
    private String ruleType;
    private Date createTime;
    private Date outTime;



    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sufferer_id", nullable = false, length = 32)
    public String getSuffererId() {
        return suffererId;
    }

    public void setSuffererId(String suffererId) {
        this.suffererId = suffererId;
    }

    @Basic
    @Column(name = "doctor_id", nullable = true, length = 32)
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "dept_id", nullable = true)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "fa_dept_name", nullable = true)
    public String getFaDeptName() {
        return faDeptName;
    }

    public void setFaDeptName(String faDeptName) {
        this.faDeptName = faDeptName;
    }

    @Basic
    @Column(name = "hit_rate1", nullable = true, length = 255)
    public String getHitRate1() {
        return hitRate1;
    }

    public void setHitRate1(String hitRate1) {
        this.hitRate1 = hitRate1;
    }

    @Basic
    @Column(name = "hit_rate3", nullable = true, length = 255)
    public String getHitRate3() {
        return hitRate3;
    }

    public void setHitRate3(String hitRate3) {
        this.hitRate3 = hitRate3;
    }

    @Basic
    @Column(name = "hit_rate5", nullable = true, length = 255)
    public String getHitRate5() {
        return hitRate5;
    }

    public void setHitRate5(String hitRate5) {
        this.hitRate5 = hitRate5;
    }

    @Basic
    @Column(name = "affirm_sickness", nullable = true, length = 255)
    public String getAffirmSickness() {
        return affirmSickness;
    }

    public void setAffirmSickness(String affirmSickness) {
        this.affirmSickness = affirmSickness;
    }


    @Column(name = "sickness_grade", nullable = true)
    public String getSicknessGrade() {
        return sicknessGrade;
    }

    public void setSicknessGrade(String sicknessGrade) {
        this.sicknessGrade = sicknessGrade;
    }

    @Basic
    @Column(name = "alarm_cause", nullable = true, length = 255)
    public String getAlarmCause() {
        return alarmCause;
    }

    public void setAlarmCause(String alarmCause) {
        this.alarmCause = alarmCause;
    }

    @Basic
    @Column(name = "alarm_condition", nullable = true, length = 255)
    public String getAlarmCondition() {
        return alarmCondition;
    }

    public void setAlarmCondition(String alarmCondition) {
        this.alarmCondition = alarmCondition;
    }

    @Basic
    @Column(name = "alarm_result", nullable = true, length = 255)
    public String getAlarmResult() {
        return alarmResult;
    }

    public void setAlarmResult(String alarmResult) {
        this.alarmResult = alarmResult;
    }

    @Basic
    @Column(name = "alarm_code", nullable = true, length = 1)
    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    @Basic
    @Column(name = "alarm_status", nullable = true, length = 1)
    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    @Basic
    @Column(name = "rule_type", nullable = true, length = 1)
    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "out_time", nullable = true)
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmHosptailLog that = (SmHosptailLog) o;
        return id == that.id &&
                Objects.equals(suffererId, that.suffererId) &&
                Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(hitRate1, that.hitRate1) &&
                Objects.equals(hitRate3, that.hitRate3) &&
                Objects.equals(hitRate5, that.hitRate5) &&
                Objects.equals(affirmSickness, that.affirmSickness) &&
                Objects.equals(alarmCause, that.alarmCause) &&
                Objects.equals(alarmCondition, that.alarmCondition) &&
                Objects.equals(alarmResult, that.alarmResult) &&
                Objects.equals(alarmCode, that.alarmCode) &&
                Objects.equals(alarmStatus, that.alarmStatus) &&
                Objects.equals(ruleType, that.ruleType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(outTime, that.outTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, suffererId, doctorId, deptId, hitRate1, hitRate3, hitRate5, affirmSickness, alarmCause, alarmCondition, alarmResult, alarmCode, alarmStatus, ruleType, createTime, outTime);
    }
}
