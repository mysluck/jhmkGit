package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 10:50
 */

@Entity
@Table(name = "rule_ruyuanjilu", schema = "jhmk_waring", catalog = "")
public class Ruyuanjilu {
    private int id;
    private String patient_id;
    private String visit_id;
    private String chief_complaint;
    private String history_of_present_illness;
    private String history_of_past_illness;
    private String social_history;
    private String history_of_family_member_diseases;
    private String menstrual_and_obstetrical_histories;
    private String physical_examination;
    private String auxiliary_examination;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "visit_id", nullable = true, length = 3)
    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    @Basic
    @Column(name = "chief_complaint", nullable = true, length = 255)
    public String getChief_complaint() {
        return chief_complaint;
    }

    public void setChief_complaint(String chief_complaint) {
        this.chief_complaint = chief_complaint;
    }

    @Basic
    @Column(name = "history_of_present_illness", nullable = true, length = 255)
    public String getHistory_of_present_illness() {
        return history_of_present_illness;
    }

    public void setHistory_of_present_illness(String history_of_present_illness) {
        this.history_of_present_illness = history_of_present_illness;
    }

    @Basic
    @Column(name = "history_of_past_illness", nullable = true, length = 255)
    public String getHistory_of_past_illness() {
        return history_of_past_illness;
    }

    public void setHistory_of_past_illness(String history_of_past_illness) {
        this.history_of_past_illness = history_of_past_illness;
    }

    @Basic
    @Column(name = "social_history", nullable = true, length = 255)
    public String getSocial_history() {
        return social_history;
    }

    public void setSocial_history(String social_history) {
        this.social_history = social_history;
    }

    @Basic
    @Column(name = "history_of_family_member_diseases", nullable = true, length = 255)
    public String getHistory_of_family_member_diseases() {
        return history_of_family_member_diseases;
    }

    public void setHistory_of_family_member_diseases(String history_of_family_member_diseases) {
        this.history_of_family_member_diseases = history_of_family_member_diseases;
    }

    @Basic
    @Column(name = "menstrual_and_obstetrical_histories", nullable = true, length = 255)
    public String getMenstrual_and_obstetrical_histories() {
        return menstrual_and_obstetrical_histories;
    }

    public void setMenstrual_and_obstetrical_histories(String menstrual_and_obstetrical_histories) {
        this.menstrual_and_obstetrical_histories = menstrual_and_obstetrical_histories;
    }

    @Basic
    @Column(name = "physical_examination", nullable = true, length = 255)
    public String getPhysical_examination() {
        return physical_examination;
    }

    public void setPhysical_examination(String physical_examination) {
        this.physical_examination = physical_examination;
    }

    @Basic
    @Column(name = "auxiliary_examination", nullable = true, length = 255)
    public String getAuxiliary_examination() {
        return auxiliary_examination;
    }

    public void setAuxiliary_examination(String auxiliary_examination) {
        this.auxiliary_examination = auxiliary_examination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruyuanjilu that = (Ruyuanjilu) o;
        return id == that.id &&
                Objects.equals(patient_id, that.patient_id) &&
                Objects.equals(visit_id, that.visit_id) &&
                Objects.equals(chief_complaint, that.chief_complaint) &&
                Objects.equals(history_of_present_illness, that.history_of_present_illness) &&
                Objects.equals(history_of_past_illness, that.history_of_past_illness) &&
                Objects.equals(social_history, that.social_history) &&
                Objects.equals(history_of_family_member_diseases, that.history_of_family_member_diseases) &&
                Objects.equals(menstrual_and_obstetrical_histories, that.menstrual_and_obstetrical_histories) &&
                Objects.equals(physical_examination, that.physical_examination) &&
                Objects.equals(auxiliary_examination, that.auxiliary_examination);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, patient_id, visit_id, chief_complaint, history_of_present_illness, history_of_past_illness, social_history, history_of_family_member_diseases, menstrual_and_obstetrical_histories, physical_examination, auxiliary_examination);
    }
}
