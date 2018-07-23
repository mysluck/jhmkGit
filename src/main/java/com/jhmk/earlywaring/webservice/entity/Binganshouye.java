package com.jhmk.earlywaring.webservice.entity;

import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/19 17:55
 */

public class Binganshouye {

    private String pat_info_sex_name;//性别
    private String pat_info_age_value;
    private String pat_info_age_value_unit;
    private String pat_info_marital_status_name;
    private String pat_info_occupation_name;
    private String pat_info_pregnancy_status;
    private String pat_visit_dept_admission_to_name;//入院科室
    private String pat_visit_dept_admission_to_code;
    private String pat_visit_dept_discharge_from_name;//出院科室
    private String pat_visit_dept_discharge_from_code;

    public String getPat_info_sex_name() {
        return pat_info_sex_name;
    }

    public void setPat_info_sex_name(String pat_info_sex_name) {
        this.pat_info_sex_name = pat_info_sex_name;
    }

    public String getPat_info_age_value() {
        return pat_info_age_value;
    }

    public void setPat_info_age_value(String pat_info_age_value) {
        this.pat_info_age_value = pat_info_age_value;
    }

    public String getPat_info_age_value_unit() {
        return pat_info_age_value_unit;
    }

    public void setPat_info_age_value_unit(String pat_info_age_value_unit) {
        this.pat_info_age_value_unit = pat_info_age_value_unit;
    }

    public String getPat_info_marital_status_name() {
        return pat_info_marital_status_name;
    }

    public void setPat_info_marital_status_name(String pat_info_marital_status_name) {
        this.pat_info_marital_status_name = pat_info_marital_status_name;
    }

    public String getPat_info_occupation_name() {
        return pat_info_occupation_name;
    }

    public void setPat_info_occupation_name(String pat_info_occupation_name) {
        this.pat_info_occupation_name = pat_info_occupation_name;
    }

    public String getPat_info_pregnancy_status() {
        return pat_info_pregnancy_status;
    }

    public void setPat_info_pregnancy_status(String pat_info_pregnancy_status) {
        this.pat_info_pregnancy_status = pat_info_pregnancy_status;
    }

    public String getPat_visit_dept_admission_to_name() {
        return pat_visit_dept_admission_to_name;
    }

    public void setPat_visit_dept_admission_to_name(String pat_visit_dept_admission_to_name) {
        this.pat_visit_dept_admission_to_name = pat_visit_dept_admission_to_name;
    }

    public String getPat_visit_dept_admission_to_code() {
        return pat_visit_dept_admission_to_code;
    }

    public void setPat_visit_dept_admission_to_code(String pat_visit_dept_admission_to_code) {
        this.pat_visit_dept_admission_to_code = pat_visit_dept_admission_to_code;
    }

    public String getPat_visit_dept_discharge_from_name() {
        return pat_visit_dept_discharge_from_name;
    }

    public void setPat_visit_dept_discharge_from_name(String pat_visit_dept_discharge_from_name) {
        this.pat_visit_dept_discharge_from_name = pat_visit_dept_discharge_from_name;
    }

    public String getPat_visit_dept_discharge_from_code() {
        return pat_visit_dept_discharge_from_code;
    }

    public void setPat_visit_dept_discharge_from_code(String pat_visit_dept_discharge_from_code) {
        this.pat_visit_dept_discharge_from_code = pat_visit_dept_discharge_from_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Binganshouye that = (Binganshouye) o;
        return Objects.equals(pat_info_sex_name, that.pat_info_sex_name) &&
                Objects.equals(pat_info_age_value, that.pat_info_age_value) &&
                Objects.equals(pat_info_age_value_unit, that.pat_info_age_value_unit) &&
                Objects.equals(pat_info_marital_status_name, that.pat_info_marital_status_name) &&
                Objects.equals(pat_info_occupation_name, that.pat_info_occupation_name) &&
                Objects.equals(pat_info_pregnancy_status, that.pat_info_pregnancy_status) &&
                Objects.equals(pat_visit_dept_admission_to_name, that.pat_visit_dept_admission_to_name) &&
                Objects.equals(pat_visit_dept_admission_to_code, that.pat_visit_dept_admission_to_code) &&
                Objects.equals(pat_visit_dept_discharge_from_name, that.pat_visit_dept_discharge_from_name) &&
                Objects.equals(pat_visit_dept_discharge_from_code, that.pat_visit_dept_discharge_from_code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pat_info_sex_name, pat_info_age_value, pat_info_age_value_unit, pat_info_marital_status_name, pat_info_occupation_name, pat_info_pregnancy_status, pat_visit_dept_admission_to_name, pat_visit_dept_admission_to_code, pat_visit_dept_discharge_from_name, pat_visit_dept_discharge_from_code);
    }
}
