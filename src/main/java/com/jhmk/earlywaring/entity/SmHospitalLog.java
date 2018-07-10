package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sm_hospital_log", schema = "jhmk_waring", catalog = "")
public class SmHospitalLog implements Serializable {
    private int id;
    private String patientId;
    private String visitId;
    private String doctorName;
    private String doctorId;
    private String deptCode;
    private String diagnosisName;
    private String sicknessGrade;
    private String alarmLevel;
    private String classification;
    private String identification;
    private String warnSource;
    private Date createTime;
    private String hintContent;
    private String ruleSource;
    private String signContent;
    private String ruleId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patient_id", nullable = false, length = 32)
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "visit_id", nullable = true, length = 11)
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    @Basic
    @Column(name = "doctor_name", nullable = true, length = 255)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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
    @Column(name = "dept_code", nullable = true, length = 32)
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Basic
    @Column(name = "diagnosis_name", nullable = true, length = 255)
    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
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
    @Column(name = "alarm_level", nullable = true, length = 8)
    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    @Basic
    @Column(name = "classification", nullable = true, length = 8)
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Basic
    @Column(name = "identification", nullable = true, length = 8)
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Basic
    @Column(name = "warn_source", nullable = true, length = 20)
    public String getWarnSource() {
        return warnSource;
    }

    public void setWarnSource(String warnSource) {
        this.warnSource = warnSource;
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
    @Column(name = "hint_content", nullable = true, length = 255)
    public String getHintContent() {
        return hintContent;
    }

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }

    @Basic
    @Column(name = "rule_source", nullable = true, length = 255)
    public String getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(String ruleSource) {
        this.ruleSource = ruleSource;
    }

    @Basic
    @Column(name = "sign_content", nullable = true, length = 255)
    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    @Basic
    @Column(name = "rule_id", nullable = true, length = 60)
    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmHospitalLog that = (SmHospitalLog) o;
        return id == that.id &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(visitId, that.visitId) &&
                Objects.equals(doctorName, that.doctorName) &&
                Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(deptCode, that.deptCode) &&
                Objects.equals(diagnosisName, that.diagnosisName) &&
                Objects.equals(sicknessGrade, that.sicknessGrade) &&
                Objects.equals(alarmLevel, that.alarmLevel) &&
                Objects.equals(classification, that.classification) &&
                Objects.equals(identification, that.identification) &&
                Objects.equals(warnSource, that.warnSource) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(hintContent, that.hintContent) &&
                Objects.equals(ruleSource, that.ruleSource) &&
                Objects.equals(signContent, that.signContent) &&
                Objects.equals(ruleId, that.ruleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, patientId, visitId, doctorName, doctorId, deptCode, diagnosisName, sicknessGrade, alarmLevel, classification, identification, warnSource, createTime, hintContent, ruleSource, signContent, ruleId);
    }

    @Override
    public String toString() {
        return "SmHospitalLog{" +
                "id=" + id +
                ", patientId='" + patientId + '\'' +
                ", visitId='" + visitId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", diagnosisName='" + diagnosisName + '\'' +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", classification='" + classification + '\'' +
                ", identification='" + identification + '\'' +
                ", warnSource='" + warnSource + '\'' +
                ", createTime=" + createTime +
                ", hintContent='" + hintContent + '\'' +
                ", ruleSource='" + ruleSource + '\'' +
                ", signContent='" + signContent + '\'' +
                ", ruleId='" + ruleId + '\'' +
                '}';
    }
}
