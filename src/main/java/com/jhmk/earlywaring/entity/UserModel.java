package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_model", schema = "jhmk_waring")
public class UserModel {
    private int umId;
    private String umTextId;
    private String umName;
    private String umShortcut;
    private Timestamp umCreateTime;
    private Timestamp umUpdateTime;
    private Integer umShow;
    private String umTextParentId;
    private Integer umParentId;
    private String umParentName;
    private Integer umHospitalId;
    private String umHospitalName;
    private String umOther;
    private Integer umLevel;
    private String umRelativeTime;
    private String umType;
    private String umIsExport;
    private String umIsVisual;
    private List<UserModel> userModels=new ArrayList<>();

    @Id
    @Column(name = "um_id", nullable = false)
    public int getUmId() {
        return umId;
    }

    public void setUmId(int umId) {
        this.umId = umId;
    }

    @Basic
    @Column(name = "um_text_id", nullable = true, length = 50)
    public String getUmTextId() {
        return umTextId;
    }

    public void setUmTextId(String umTextId) {
        this.umTextId = umTextId;
    }

    @Basic
    @Column(name = "um_name", nullable = true, length = 200)
    public String getUmName() {
        return umName;
    }

    public void setUmName(String umName) {
        this.umName = umName;
    }

    @Basic
    @Column(name = "um_shortcut", nullable = true, length = 200)
    public String getUmShortcut() {
        return umShortcut;
    }

    public void setUmShortcut(String umShortcut) {
        this.umShortcut = umShortcut;
    }

    @Basic
    @Column(name = "um_create_time", nullable = true)
    public Timestamp getUmCreateTime() {
        return umCreateTime;
    }

    public void setUmCreateTime(Timestamp umCreateTime) {
        this.umCreateTime = umCreateTime;
    }

    @Basic
    @Column(name = "um_update_time", nullable = false)
    public Timestamp getUmUpdateTime() {
        return umUpdateTime;
    }

    public void setUmUpdateTime(Timestamp umUpdateTime) {
        this.umUpdateTime = umUpdateTime;
    }

    @Basic
    @Column(name = "um_show", nullable = true)
    public Integer getUmShow() {
        return umShow;
    }

    public void setUmShow(Integer umShow) {
        this.umShow = umShow;
    }

    @Basic
    @Column(name = "um_text_parent_id", nullable = true, length = 50)
    public String getUmTextParentId() {
        return umTextParentId;
    }

    public void setUmTextParentId(String umTextParentId) {
        this.umTextParentId = umTextParentId;
    }

    @Basic
    @Column(name = "um_parent_id", nullable = true)
    public Integer getUmParentId() {
        return umParentId;
    }

    public void setUmParentId(Integer umParentId) {
        this.umParentId = umParentId;
    }

    @Basic
    @Column(name = "um_parent_name", nullable = true, length = 200)
    public String getUmParentName() {
        return umParentName;
    }

    public void setUmParentName(String umParentName) {
        this.umParentName = umParentName;
    }

    @Basic
    @Column(name = "um_hospital_id", nullable = true)
    public Integer getUmHospitalId() {
        return umHospitalId;
    }

    public void setUmHospitalId(Integer umHospitalId) {
        this.umHospitalId = umHospitalId;
    }

    @Basic
    @Column(name = "um_hospital_name", nullable = true, length = 200)
    public String getUmHospitalName() {
        return umHospitalName;
    }

    public void setUmHospitalName(String umHospitalName) {
        this.umHospitalName = umHospitalName;
    }

    @Basic
    @Column(name = "um_other", nullable = true, length = 200)
    public String getUmOther() {
        return umOther;
    }

    public void setUmOther(String umOther) {
        this.umOther = umOther;
    }

    @Basic
    @Column(name = "um_level", nullable = true)
    public Integer getUmLevel() {
        return umLevel;
    }

    public void setUmLevel(Integer umLevel) {
        this.umLevel = umLevel;
    }

    @Basic
    @Column(name = "um_relative_time", nullable = true, length = 50)
    public String getUmRelativeTime() {
        return umRelativeTime;
    }

    public void setUmRelativeTime(String umRelativeTime) {
        this.umRelativeTime = umRelativeTime;
    }

    @Basic
    @Column(name = "um_type", nullable = true, length = 50)
    public String getUmType() {
        return umType;
    }

    public void setUmType(String umType) {
        this.umType = umType;
    }

    @Basic
    @Column(name = "um_is_export", nullable = true, length = 50)
    public String getUmIsExport() {
        return umIsExport;
    }

    public void setUmIsExport(String umIsExport) {
        this.umIsExport = umIsExport;
    }

    @Basic
    @Column(name = "um_is_visual", nullable = true, length = 50)
    public String getUmIsVisual() {
        return umIsVisual;
    }

    public void setUmIsVisual(String umIsVisual) {
        this.umIsVisual = umIsVisual;
    }

    @Transient
    public List<UserModel> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return umId == userModel.umId &&
                Objects.equals(umTextId, userModel.umTextId) &&
                Objects.equals(umName, userModel.umName) &&
                Objects.equals(umShortcut, userModel.umShortcut) &&
                Objects.equals(umCreateTime, userModel.umCreateTime) &&
                Objects.equals(umUpdateTime, userModel.umUpdateTime) &&
                Objects.equals(umShow, userModel.umShow) &&
                Objects.equals(umTextParentId, userModel.umTextParentId) &&
                Objects.equals(umParentId, userModel.umParentId) &&
                Objects.equals(umParentName, userModel.umParentName) &&
                Objects.equals(umHospitalId, userModel.umHospitalId) &&
                Objects.equals(umHospitalName, userModel.umHospitalName) &&
                Objects.equals(umOther, userModel.umOther) &&
                Objects.equals(umLevel, userModel.umLevel) &&
                Objects.equals(umRelativeTime, userModel.umRelativeTime) &&
                Objects.equals(umType, userModel.umType) &&
                Objects.equals(umIsExport, userModel.umIsExport) &&
                Objects.equals(umIsVisual, userModel.umIsVisual);
    }

    @Override
    public int hashCode() {

        return Objects.hash(umId, umTextId, umName, umShortcut, umCreateTime, umUpdateTime, umShow, umTextParentId, umParentId, umParentName, umHospitalId, umHospitalName, umOther, umLevel, umRelativeTime, umType, umIsExport, umIsVisual);
    }
}
