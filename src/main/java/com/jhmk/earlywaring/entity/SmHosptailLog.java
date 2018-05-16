package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sm_hosptail_log", schema = "jhmk_waring")
public class SmHosptailLog {
    private int id;
    private String suffererId;
    private String doctorId;
    private String deptId;
    private String affirmSickness;
    private String sicknessGrade;
    private String alarmCause;
    private String alarmCondition;
    private String alarmResult;
    private String alarmCode;
    private String alarmStatus;
    private String ruleType;
    private Date createTime;
    private Date outTime;
    private String faDeptName;

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
    @Column(name = "dept_id", nullable = true, length = 32)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "affirm_sickness", nullable = true, length = 255)
    public String getAffirmSickness() {
        return affirmSickness;
    }

    public void setAffirmSickness(String affirmSickness) {
        this.affirmSickness = affirmSickness;
    }

    @Basic
    @Column(name = "sickness_grade", nullable = true, length = 2)
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

    @Basic
    @Column(name = "fa_dept_name", nullable = true, length = 32)
    public String getFaDeptName() {
        return faDeptName;
    }

    public void setFaDeptName(String faDeptName) {
        this.faDeptName = faDeptName;
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
                Objects.equals(affirmSickness, that.affirmSickness) &&
                Objects.equals(sicknessGrade, that.sicknessGrade) &&
                Objects.equals(alarmCause, that.alarmCause) &&
                Objects.equals(alarmCondition, that.alarmCondition) &&
                Objects.equals(alarmResult, that.alarmResult) &&
                Objects.equals(alarmCode, that.alarmCode) &&
                Objects.equals(alarmStatus, that.alarmStatus) &&
                Objects.equals(ruleType, that.ruleType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(outTime, that.outTime) &&
                Objects.equals(faDeptName, that.faDeptName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, suffererId, doctorId, deptId, affirmSickness, sicknessGrade, alarmCause, alarmCondition, alarmResult, alarmCode, alarmStatus, ruleType, createTime, outTime, faDeptName);
    }
}
