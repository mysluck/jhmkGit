package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 10:50
 */

@Entity
@Table(name = "rule_binglizhenduan", schema = "jhmk_waring")
public class Binglizhenduan {
    private int id;
    private String visit_id;
    private String patient_id;
    private String diagnosis_name;
    private String diagnosis_desc;
    private String diagnosis_time;
    private String diagnosis_num;
    private String diagnosis_sub_num;
    private String diagnosis_type_name;

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
    @Column(name = "visit_id", nullable = true, length = 50)
    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
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
    @Column(name = "diagnosis_name", nullable = true, length = 50)
    public String getDiagnosis_name() {
        return diagnosis_name;
    }

    public void setDiagnosis_name(String diagnosis_name) {
        this.diagnosis_name = diagnosis_name;
    }

    @Basic
    @Column(name = "diagnosis_desc", nullable = true, length = 50)
    public String getDiagnosis_desc() {
        return diagnosis_desc;
    }

    public void setDiagnosis_desc(String diagnosis_desc) {
        this.diagnosis_desc = diagnosis_desc;
    }

    @Basic
    @Column(name = "diagnosis_time", nullable = true, length = 50)
    public String getDiagnosis_time() {
        return diagnosis_time;
    }

    public void setDiagnosis_time(String diagnosis_time) {
        this.diagnosis_time = diagnosis_time;
    }

    @Basic
    @Column(name = "diagnosis_num", nullable = true, length = 50)
    public String getDiagnosis_num() {
        return diagnosis_num;
    }

    public void setDiagnosis_num(String diagnosis_num) {
        this.diagnosis_num = diagnosis_num;
    }

    @Basic
    @Column(name = "diagnosis_sub_num", nullable = true, length = 50)
    public String getDiagnosis_sub_num() {
        return diagnosis_sub_num;
    }

    public void setDiagnosis_sub_num(String diagnosis_sub_num) {
        this.diagnosis_sub_num = diagnosis_sub_num;
    }

    @Basic
    @Column(name = "diagnosis_type_name", nullable = true, length = 50)
    public String getDiagnosis_type_name() {
        return diagnosis_type_name;
    }

    public void setDiagnosis_type_name(String diagnosis_type_name) {
        this.diagnosis_type_name = diagnosis_type_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Binglizhenduan that = (Binglizhenduan) o;
        return id == that.id &&
                Objects.equals(visit_id, that.visit_id) &&
                Objects.equals(patient_id, that.patient_id) &&
                Objects.equals(diagnosis_name, that.diagnosis_name) &&
                Objects.equals(diagnosis_desc, that.diagnosis_desc) &&
                Objects.equals(diagnosis_time, that.diagnosis_time) &&
                Objects.equals(diagnosis_num, that.diagnosis_num) &&
                Objects.equals(diagnosis_sub_num, that.diagnosis_sub_num) &&
                Objects.equals(diagnosis_type_name, that.diagnosis_type_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, visit_id, patient_id, diagnosis_name, diagnosis_desc, diagnosis_time, diagnosis_num, diagnosis_sub_num, diagnosis_type_name);
    }
}
