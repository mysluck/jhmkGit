package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 10:53
 */

@Entity
@Table(name = "rule_bingchengjilu", schema = "jhmk_waring")
public class Bingchengjilu {
    private int id;
    private String patient_id;
    private String visit_id;
    private String emr_code;
    private String template_content;

    @Id
    @Column(name = "id", nullable = false)
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
    @Column(name = "visit_id", nullable = true, length = 3)
    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    @Basic
    @Column(name = "emr_code", nullable = true, length = 255)
    public String getEmr_code() {
        return emr_code;
    }

    public void setEmr_code(String emr_code) {
        this.emr_code = emr_code;
    }

    @Basic
    @Column(name = "template_content", nullable = true, length = 255)
    public String getTemplate_content() {
        return template_content;
    }

    public void setTemplate_content(String template_content) {
        this.template_content = template_content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bingchengjilu that = (Bingchengjilu) o;
        return id == that.id &&
                Objects.equals(patient_id, that.patient_id) &&
                Objects.equals(visit_id, that.visit_id) &&
                Objects.equals(emr_code, that.emr_code) &&
                Objects.equals(template_content, that.template_content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, patient_id, visit_id, emr_code, template_content);
    }
}
