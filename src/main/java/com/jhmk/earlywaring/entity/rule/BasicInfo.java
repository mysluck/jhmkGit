package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 10:50
 */

@Entity
@Table(name = "rule_basic_info", schema = "jhmk_waring")
public class BasicInfo {
    private int id;
    private String doctor_id;
    private String doctor_name;
    private String dept_name;
    private String patient_id;
    private String visit_id;
    private String pageSource;
    private String warnSource;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doctor_id", nullable = true, length = 50)
    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    @Basic
    @Column(name = "doctor_name", nullable = true, length = 50)
    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    @Basic
    @Column(name = "dept_name", nullable = true, length = 50)
    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    @Basic
    @Column(name = "patient_id", nullable = true, length = 50)
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }


    @Basic
    @Column(name = "visit_id", nullable = true, length = 50)
    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    @Basic
    @Column(name = "page_source", nullable = true, length = 50)
    public String getPageSource() {
        return pageSource;
    }

    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

    @Basic
    @Column(name = "warn_source", nullable = true, length = 50)
    public String getWarnSource() {
        return warnSource;
    }

    public void setWarnSource(String warnSource) {
        this.warnSource = warnSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicInfo basicInfo = (BasicInfo) o;
        return id == basicInfo.id &&
                Objects.equals(doctor_id, basicInfo.doctor_id) &&
                Objects.equals(doctor_name, basicInfo.doctor_name) &&
                Objects.equals(dept_name, basicInfo.dept_name) &&
                Objects.equals(patient_id, basicInfo.patient_id) &&
                Objects.equals(visit_id, basicInfo.visit_id) &&
                Objects.equals(pageSource, basicInfo.pageSource) &&
                Objects.equals(warnSource, basicInfo.warnSource);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, doctor_id, doctor_name, dept_name, patient_id, visit_id, pageSource, warnSource);
    }
}
