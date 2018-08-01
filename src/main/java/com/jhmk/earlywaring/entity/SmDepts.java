package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sm_depts", schema = "jhmk_waring")
public class SmDepts {
    private String serialNo;
    private String deptCode;
    private String deptName;
    private String deptAlias;
    private String clinicAttr;
    private String outpOrInp;
    private String internalOrSergery;
    private String inputCode;
    private String typeCode;
    private String location;
    private String displayOrder;
    private String totalBedNum;
    private String totalBedNumOld;
    private String synchroFlag;
    private String deptEmr;
    private String deptEmrDateTime;
    private String deptConsult;
    private String deptType;
    private String errorStyle;
    private String viewSequence;
    private String hospitalNo;
    private String pym;
    private String wbm;
    private String isGraveDept;
    private String state;
    private String wtStatus;
    private String chineseWesternStyle;
    private String deptgroup;
    private String consultAudit;
    private String isConsult;
    private String orderNo;
    private String deptPhone;
    private String isWardGroup;

    @Basic
    @Column(name = "SERIAL_NO", nullable = true, length = 255)
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Basic
    @Id
    @Column(name = "DEPT_CODE", nullable = true, length = 255)
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Basic
    @Column(name = "DEPT_NAME", nullable = true, length = 255)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "DEPT_ALIAS", nullable = true, length = 255)
    public String getDeptAlias() {
        return deptAlias;
    }

    public void setDeptAlias(String deptAlias) {
        this.deptAlias = deptAlias;
    }

    @Basic
    @Column(name = "CLINIC_ATTR", nullable = true, length = 255)
    public String getClinicAttr() {
        return clinicAttr;
    }

    public void setClinicAttr(String clinicAttr) {
        this.clinicAttr = clinicAttr;
    }

    @Basic
    @Column(name = "OUTP_OR_INP", nullable = true, length = 255)
    public String getOutpOrInp() {
        return outpOrInp;
    }

    public void setOutpOrInp(String outpOrInp) {
        this.outpOrInp = outpOrInp;
    }

    @Basic
    @Column(name = "INTERNAL_OR_SERGERY", nullable = true, length = 255)
    public String getInternalOrSergery() {
        return internalOrSergery;
    }

    public void setInternalOrSergery(String internalOrSergery) {
        this.internalOrSergery = internalOrSergery;
    }

    @Basic
    @Column(name = "INPUT_CODE", nullable = true, length = 255)
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    @Basic
    @Column(name = "TYPE_CODE", nullable = true, length = 255)
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Basic
    @Column(name = "LOCATION", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "DISPLAY_ORDER", nullable = true, length = 255)
    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Basic
    @Column(name = "TOTAL_BED_NUM", nullable = true, length = 255)
    public String getTotalBedNum() {
        return totalBedNum;
    }

    public void setTotalBedNum(String totalBedNum) {
        this.totalBedNum = totalBedNum;
    }

    @Basic
    @Column(name = "TOTAL_BED_NUM_OLD", nullable = true, length = 255)
    public String getTotalBedNumOld() {
        return totalBedNumOld;
    }

    public void setTotalBedNumOld(String totalBedNumOld) {
        this.totalBedNumOld = totalBedNumOld;
    }

    @Basic
    @Column(name = "SYNCHRO_FLAG", nullable = true, length = 255)
    public String getSynchroFlag() {
        return synchroFlag;
    }

    public void setSynchroFlag(String synchroFlag) {
        this.synchroFlag = synchroFlag;
    }

    @Basic
    @Column(name = "DEPT_EMR", nullable = true, length = 255)
    public String getDeptEmr() {
        return deptEmr;
    }

    public void setDeptEmr(String deptEmr) {
        this.deptEmr = deptEmr;
    }

    @Basic
    @Column(name = "DEPT_EMR_DATE_TIME", nullable = true, length = 255)
    public String getDeptEmrDateTime() {
        return deptEmrDateTime;
    }

    public void setDeptEmrDateTime(String deptEmrDateTime) {
        this.deptEmrDateTime = deptEmrDateTime;
    }

    @Basic
    @Column(name = "DEPT_CONSULT", nullable = true, length = 255)
    public String getDeptConsult() {
        return deptConsult;
    }

    public void setDeptConsult(String deptConsult) {
        this.deptConsult = deptConsult;
    }

    @Basic
    @Column(name = "DEPT_TYPE", nullable = true, length = 255)
    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    @Basic
    @Column(name = "ERROR_STYLE", nullable = true, length = 255)
    public String getErrorStyle() {
        return errorStyle;
    }

    public void setErrorStyle(String errorStyle) {
        this.errorStyle = errorStyle;
    }

    @Basic
    @Column(name = "VIEW_SEQUENCE", nullable = true, length = 255)
    public String getViewSequence() {
        return viewSequence;
    }

    public void setViewSequence(String viewSequence) {
        this.viewSequence = viewSequence;
    }

    @Basic
    @Column(name = "HOSPITAL_NO", nullable = true, length = 255)
    public String getHospitalNo() {
        return hospitalNo;
    }

    public void setHospitalNo(String hospitalNo) {
        this.hospitalNo = hospitalNo;
    }

    @Basic
    @Column(name = "PYM", nullable = true, length = 255)
    public String getPym() {
        return pym;
    }

    public void setPym(String pym) {
        this.pym = pym;
    }

    @Basic
    @Column(name = "WBM", nullable = true, length = 255)
    public String getWbm() {
        return wbm;
    }

    public void setWbm(String wbm) {
        this.wbm = wbm;
    }

    @Basic
    @Column(name = "IS_GRAVE_DEPT", nullable = true, length = 255)
    public String getIsGraveDept() {
        return isGraveDept;
    }

    public void setIsGraveDept(String isGraveDept) {
        this.isGraveDept = isGraveDept;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 255)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "WT_STATUS", nullable = true, length = 255)
    public String getWtStatus() {
        return wtStatus;
    }

    public void setWtStatus(String wtStatus) {
        this.wtStatus = wtStatus;
    }

    @Basic
    @Column(name = "CHINESE_WESTERN_STYLE", nullable = true, length = 255)
    public String getChineseWesternStyle() {
        return chineseWesternStyle;
    }

    public void setChineseWesternStyle(String chineseWesternStyle) {
        this.chineseWesternStyle = chineseWesternStyle;
    }

    @Basic
    @Column(name = "DEPTGROUP", nullable = true, length = 255)
    public String getDeptgroup() {
        return deptgroup;
    }

    public void setDeptgroup(String deptgroup) {
        this.deptgroup = deptgroup;
    }

    @Basic
    @Column(name = "CONSULT_AUDIT", nullable = true, length = 255)
    public String getConsultAudit() {
        return consultAudit;
    }

    public void setConsultAudit(String consultAudit) {
        this.consultAudit = consultAudit;
    }

    @Basic
    @Column(name = "IS_CONSULT", nullable = true, length = 255)
    public String getIsConsult() {
        return isConsult;
    }

    public void setIsConsult(String isConsult) {
        this.isConsult = isConsult;
    }

    @Basic
    @Column(name = "ORDER_NO", nullable = true, length = 255)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "DEPT_PHONE", nullable = true, length = 255)
    public String getDeptPhone() {
        return deptPhone;
    }

    public void setDeptPhone(String deptPhone) {
        this.deptPhone = deptPhone;
    }

    @Basic
    @Column(name = "IS_WARD_GROUP", nullable = true, length = 255)
    public String getIsWardGroup() {
        return isWardGroup;
    }

    public void setIsWardGroup(String isWardGroup) {
        this.isWardGroup = isWardGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmDepts smDepts = (SmDepts) o;
        return Objects.equals(serialNo, smDepts.serialNo) &&
                Objects.equals(deptCode, smDepts.deptCode) &&
                Objects.equals(deptName, smDepts.deptName) &&
                Objects.equals(deptAlias, smDepts.deptAlias) &&
                Objects.equals(clinicAttr, smDepts.clinicAttr) &&
                Objects.equals(outpOrInp, smDepts.outpOrInp) &&
                Objects.equals(internalOrSergery, smDepts.internalOrSergery) &&
                Objects.equals(inputCode, smDepts.inputCode) &&
                Objects.equals(typeCode, smDepts.typeCode) &&
                Objects.equals(location, smDepts.location) &&
                Objects.equals(displayOrder, smDepts.displayOrder) &&
                Objects.equals(totalBedNum, smDepts.totalBedNum) &&
                Objects.equals(totalBedNumOld, smDepts.totalBedNumOld) &&
                Objects.equals(synchroFlag, smDepts.synchroFlag) &&
                Objects.equals(deptEmr, smDepts.deptEmr) &&
                Objects.equals(deptEmrDateTime, smDepts.deptEmrDateTime) &&
                Objects.equals(deptConsult, smDepts.deptConsult) &&
                Objects.equals(deptType, smDepts.deptType) &&
                Objects.equals(errorStyle, smDepts.errorStyle) &&
                Objects.equals(viewSequence, smDepts.viewSequence) &&
                Objects.equals(hospitalNo, smDepts.hospitalNo) &&
                Objects.equals(pym, smDepts.pym) &&
                Objects.equals(wbm, smDepts.wbm) &&
                Objects.equals(isGraveDept, smDepts.isGraveDept) &&
                Objects.equals(state, smDepts.state) &&
                Objects.equals(wtStatus, smDepts.wtStatus) &&
                Objects.equals(chineseWesternStyle, smDepts.chineseWesternStyle) &&
                Objects.equals(deptgroup, smDepts.deptgroup) &&
                Objects.equals(consultAudit, smDepts.consultAudit) &&
                Objects.equals(isConsult, smDepts.isConsult) &&
                Objects.equals(orderNo, smDepts.orderNo) &&
                Objects.equals(deptPhone, smDepts.deptPhone) &&
                Objects.equals(isWardGroup, smDepts.isWardGroup);
    }

    @Override
    public int hashCode() {

        return Objects.hash(serialNo, deptCode, deptName, deptAlias, clinicAttr, outpOrInp, internalOrSergery, inputCode, typeCode, location, displayOrder, totalBedNum, totalBedNumOld, synchroFlag, deptEmr, deptEmrDateTime, deptConsult, deptType, errorStyle, viewSequence, hospitalNo, pym, wbm, isGraveDept, state, wtStatus, chineseWesternStyle, deptgroup, consultAudit, isConsult, orderNo, deptPhone, isWardGroup);
    }
}
