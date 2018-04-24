package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dept_rel", schema = "jhmk_waring")
public class DeptRel {
    private String serialNo;
    private String wardCode;
    private String deptCode;
    private String wardName;
    private String deptName;
    private String wardInputCode;
    private String deptInputCode;

    @Basic
    @Column(name = "SERIAL_NO", nullable = true, length = 255)
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Id
    @Column(name = "WARD_CODE", nullable = true, length = 255)
    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    @Basic
    @Column(name = "DEPT_CODE", nullable = true, length = 255)
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Basic
    @Column(name = "WARD_NAME", nullable = true, length = 255)
    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
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
    @Column(name = "WARD_INPUT_CODE", nullable = true, length = 255)
    public String getWardInputCode() {
        return wardInputCode;
    }

    public void setWardInputCode(String wardInputCode) {
        this.wardInputCode = wardInputCode;
    }

    @Basic
    @Column(name = "DEPT_INPUT_CODE", nullable = true, length = 255)
    public String getDeptInputCode() {
        return deptInputCode;
    }

    public void setDeptInputCode(String deptInputCode) {
        this.deptInputCode = deptInputCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptRel deptRel = (DeptRel) o;
        return Objects.equals(serialNo, deptRel.serialNo) &&
                Objects.equals(wardCode, deptRel.wardCode) &&
                Objects.equals(deptCode, deptRel.deptCode) &&
                Objects.equals(wardName, deptRel.wardName) &&
                Objects.equals(deptName, deptRel.deptName) &&
                Objects.equals(wardInputCode, deptRel.wardInputCode) &&
                Objects.equals(deptInputCode, deptRel.deptInputCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(serialNo, wardCode, deptCode, wardName, deptName, wardInputCode, deptInputCode);
    }
}
