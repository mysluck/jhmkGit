package com.jhmk.earlywaring.entity.rule;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

public class Zhenduan {


    private String diagnosis_name;
    private String diagnosis_time;
    private String diagnosis_num;
    private String diagnosis_type_name;

    public String getDiagnosis_name() {
        return diagnosis_name;
    }

    public void setDiagnosis_name(String diagnosis_name) {
        this.diagnosis_name = diagnosis_name;
    }

    public String getDiagnosis_time() {
        return diagnosis_time;
    }

    public void setDiagnosis_time(String diagnosis_time) {
        this.diagnosis_time = diagnosis_time;
    }

    public String getDiagnosis_num() {
        return diagnosis_num;
    }

    public void setDiagnosis_num(String diagnosis_num) {
        this.diagnosis_num = diagnosis_num;
    }

    public String getDiagnosis_type_name() {
        return diagnosis_type_name;
    }

    public void setDiagnosis_type_name(String diagnosis_type_name) {
        this.diagnosis_type_name = diagnosis_type_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Zhenduan zhenduan = (Zhenduan) o;
        return Objects.equals(diagnosis_name, zhenduan.diagnosis_name) &&
                Objects.equals(diagnosis_time, zhenduan.diagnosis_time) &&
                Objects.equals(diagnosis_num, zhenduan.diagnosis_num) &&
                Objects.equals(diagnosis_type_name, zhenduan.diagnosis_type_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(diagnosis_name, diagnosis_time, diagnosis_num, diagnosis_type_name);
    }
}
