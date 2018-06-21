package com.jhmk.earlywaring.entity.rule;

public class Ruyuanjilu {

    private String chief_complaint;//主诉
    private String history_of_present_illness;//现病史
    private String history_of_past_illness;//既往史
    private String social_history;//个人史
    private String history_of_family_member_diseases;//家族史
    private String menstrual_and_obstetrical_histories;//婚姻史
    private String physical_examination;//辅助检查（身体检查）
    private String auxiliary_examination;//专科检查

    public String getChief_complaint() {
        return chief_complaint;
    }

    public void setChief_complaint(String chief_complaint) {
        this.chief_complaint = chief_complaint;
    }

    public String getHistory_of_present_illness() {
        return history_of_present_illness;
    }

    public void setHistory_of_present_illness(String history_of_present_illness) {
        this.history_of_present_illness = history_of_present_illness;
    }

    public String getHistory_of_past_illness() {
        return history_of_past_illness;
    }

    public void setHistory_of_past_illness(String history_of_past_illness) {
        this.history_of_past_illness = history_of_past_illness;
    }

    public String getSocial_history() {
        return social_history;
    }

    public void setSocial_history(String social_history) {
        this.social_history = social_history;
    }

    public String getHistory_of_family_member_diseases() {
        return history_of_family_member_diseases;
    }

    public void setHistory_of_family_member_diseases(String history_of_family_member_diseases) {
        this.history_of_family_member_diseases = history_of_family_member_diseases;
    }

    public String getMenstrual_and_obstetrical_histories() {
        return menstrual_and_obstetrical_histories;
    }

    public void setMenstrual_and_obstetrical_histories(String menstrual_and_obstetrical_histories) {
        this.menstrual_and_obstetrical_histories = menstrual_and_obstetrical_histories;
    }

    public String getPhysical_examination() {
        return physical_examination;
    }

    public void setPhysical_examination(String physical_examination) {
        this.physical_examination = physical_examination;
    }

    public String getAuxiliary_examination() {
        return auxiliary_examination;
    }

    public void setAuxiliary_examination(String auxiliary_examination) {
        this.auxiliary_examination = auxiliary_examination;
    }
}
