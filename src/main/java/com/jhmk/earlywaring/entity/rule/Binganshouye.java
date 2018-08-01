package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 13:46
 */

@Entity
@Table(name = "rule_binganshouye", schema = "jhmk_waring", catalog = "")
public class Binganshouye {
    private int id;
    private String patient_id;
    private String visit_id;
    private String pat_info_sex_name;
    private String pat_info_age_value;
    private String pat_info_age_value_unit;
    private String pat_info_marital_status_name;
    private String pat_info_occupation_name;
    private String pat_info_pregnancy_status;
    private String pat_visit_dept_admission_to_name;
    private String pat_visit_dept_discharge_from_name;
    private String drug_allergy_name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "patient_id", nullable = false, length = 50)
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }


    @Column(name = "visit_id", nullable = true, length = 3)
    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }


    @Column(name = "pat_info_sex_name", nullable = true, length = 8)
    public String getPat_info_sex_name() {
        return pat_info_sex_name;
    }

    public void setPat_info_sex_name(String pat_info_sex_name) {
        this.pat_info_sex_name = pat_info_sex_name;
    }

    @Column(name = "pat_info_age_value", nullable = true, length = 8)
    public String getPat_info_age_value() {
        return pat_info_age_value;
    }

    public void setPat_info_age_value(String pat_info_age_value) {
        this.pat_info_age_value = pat_info_age_value;
    }

    @Column(name = "pat_info_age_value_unit", nullable = true, length = 8)
    public String getPat_info_age_value_unit() {
        return pat_info_age_value_unit;
    }

    public void setPat_info_age_value_unit(String pat_info_age_value_unit) {
        this.pat_info_age_value_unit = pat_info_age_value_unit;
    }

    @Column(name = "pat_info_marital_status_name", nullable = true, length = 8)
    public String getPat_info_marital_status_name() {
        return pat_info_marital_status_name;
    }

    public void setPat_info_marital_status_name(String pat_info_marital_status_name) {
        this.pat_info_marital_status_name = pat_info_marital_status_name;
    }

    @Column(name = "pat_info_occupation_name", nullable = true, length = 8)
    public String getPat_info_occupation_name() {
        return pat_info_occupation_name;
    }

    public void setPat_info_occupation_name(String pat_info_occupation_name) {
        this.pat_info_occupation_name = pat_info_occupation_name;
    }


    @Column(name = "pat_info_pregnancy_status", nullable = true, length = 8)

    public String getPat_info_pregnancy_status() {
        return pat_info_pregnancy_status;
    }

    public void setPat_info_pregnancy_status(String pat_info_pregnancy_status) {
        this.pat_info_pregnancy_status = pat_info_pregnancy_status;
    }

    @Column(name = "pat_visit_dept_admission_to_name", nullable = true, length = 20)
    public String getPat_visit_dept_admission_to_name() {
        return pat_visit_dept_admission_to_name;
    }

    public void setPat_visit_dept_admission_to_name(String pat_visit_dept_admission_to_name) {
        this.pat_visit_dept_admission_to_name = pat_visit_dept_admission_to_name;
    }


    @Column(name = "drug_allergy_name", nullable = true, length = 50)
    public String getDrug_allergy_name() {
        return drug_allergy_name;
    }

    public void setDrug_allergy_name(String drug_allergy_name) {
        this.drug_allergy_name = drug_allergy_name;
    }

    @Column(name = "pat_visit_dept_discharge_from_name", nullable = true, length = 20)
    public String getPat_visit_dept_discharge_from_name() {
        return pat_visit_dept_discharge_from_name;
    }

    public void setPat_visit_dept_discharge_from_name(String pat_visit_dept_discharge_from_name) {
        this.pat_visit_dept_discharge_from_name = pat_visit_dept_discharge_from_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Binganshouye that = (Binganshouye) o;
        return id == that.id &&
                Objects.equals(patient_id, that.patient_id) &&
                Objects.equals(visit_id, that.visit_id) &&
                Objects.equals(pat_info_sex_name, that.pat_info_sex_name) &&
                Objects.equals(pat_info_age_value, that.pat_info_age_value) &&
                Objects.equals(pat_info_age_value_unit, that.pat_info_age_value_unit) &&
                Objects.equals(pat_info_marital_status_name, that.pat_info_marital_status_name) &&
                Objects.equals(pat_info_occupation_name, that.pat_info_occupation_name) &&
                Objects.equals(pat_info_pregnancy_status, that.pat_info_pregnancy_status) &&
                Objects.equals(pat_visit_dept_admission_to_name, that.pat_visit_dept_admission_to_name) &&
                Objects.equals(pat_visit_dept_discharge_from_name, that.pat_visit_dept_discharge_from_name) &&
                Objects.equals(drug_allergy_name, that.drug_allergy_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, patient_id, visit_id, pat_info_sex_name, pat_info_age_value, pat_info_age_value_unit, pat_info_marital_status_name, pat_info_occupation_name, pat_info_pregnancy_status, pat_visit_dept_admission_to_name, pat_visit_dept_discharge_from_name, drug_allergy_name);
    }
}
