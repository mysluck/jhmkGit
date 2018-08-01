package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 11:46
 */

@Entity
@Table(name = "rule_yizhu", schema = "jhmk_waring", catalog = "")
public class Yizhu {
    private int id;
    private String patient_id;
    private String visit_id;
    private String order_item_name;
    private String order_begin_time;
    private String order_end_time;
    private String frequency_name;
    private String frequency_code;
    private String dosage_form;
    private String specification;
    private String drug_amount_value;
    private String order_properties_name;
    private String duration_value;

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
    @Column(name = "patient_id", nullable = false, length = 50)
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    @Basic
    @Column(name = "visit_id", nullable = false, length = 50)
    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    @Basic
    @Column(name = "order_item_name", nullable = false, length = 50)
    public String getOrder_item_name() {
        return order_item_name;
    }

    public void setOrder_item_name(String order_item_name) {
        this.order_item_name = order_item_name;
    }

    @Basic
    @Column(name = "order_begin_time", nullable = true, length = 50)
    public String getOrder_begin_time() {
        return order_begin_time;
    }

    public void setOrder_begin_time(String order_begin_time) {
        this.order_begin_time = order_begin_time;
    }

    @Basic
    @Column(name = "order_end_time", nullable = true, length = 50)
    public String getOrder_end_time() {
        return order_end_time;
    }

    public void setOrder_end_time(String order_end_time) {
        this.order_end_time = order_end_time;
    }

    @Basic
    @Column(name = "frequency_name", nullable = true, length = 50)
    public String getFrequency_name() {
        return frequency_name;
    }

    public void setFrequency_name(String frequency_name) {
        this.frequency_name = frequency_name;
    }

    @Basic
    @Column(name = "frequency_code", nullable = true, length = 50)
    public String getFrequency_code() {
        return frequency_code;
    }

    public void setFrequency_code(String frequency_code) {
        this.frequency_code = frequency_code;
    }

    @Basic
    @Column(name = "dosage_form", nullable = true, length = 50)
    public String getDosage_form() {
        return dosage_form;
    }

    public void setDosage_form(String dosage_form) {
        this.dosage_form = dosage_form;
    }

    @Basic
    @Column(name = "specification", nullable = true, length = 50)
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Basic
    @Column(name = "drug_amount_value", nullable = true, length = 50)
    public String getDrug_amount_value() {
        return drug_amount_value;
    }

    public void setDrug_amount_value(String drug_amount_value) {
        this.drug_amount_value = drug_amount_value;
    }

    @Basic
    @Column(name = "order_properties_name", nullable = true, length = 50)
    public String getOrder_properties_name() {
        return order_properties_name;
    }

    public void setOrder_properties_name(String order_properties_name) {
        this.order_properties_name = order_properties_name;
    }

    @Basic
    @Column(name = "duration_value", nullable = true, length = 50)
    public String getDuration_value() {
        return duration_value;
    }

    public void setDuration_value(String duration_value) {
        this.duration_value = duration_value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Yizhu yizhu = (Yizhu) o;
        return id == yizhu.id &&
                Objects.equals(patient_id, yizhu.patient_id) &&
                Objects.equals(visit_id, yizhu.visit_id) &&
                Objects.equals(order_item_name, yizhu.order_item_name) &&
                Objects.equals(order_begin_time, yizhu.order_begin_time) &&
                Objects.equals(order_end_time, yizhu.order_end_time) &&
                Objects.equals(frequency_name, yizhu.frequency_name) &&
                Objects.equals(frequency_code, yizhu.frequency_code) &&
                Objects.equals(dosage_form, yizhu.dosage_form) &&
                Objects.equals(specification, yizhu.specification) &&
                Objects.equals(drug_amount_value, yizhu.drug_amount_value) &&
                Objects.equals(order_properties_name, yizhu.order_properties_name) &&
                Objects.equals(duration_value, yizhu.duration_value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, patient_id, visit_id, order_item_name, order_begin_time, order_end_time, frequency_name, frequency_code, dosage_form, specification, drug_amount_value, order_properties_name, duration_value);
    }
}