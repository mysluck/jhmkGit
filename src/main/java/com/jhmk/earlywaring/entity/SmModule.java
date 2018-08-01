package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sm_module", schema = "jhmk_waring")
public class SmModule {
    private String modCode;
    private String modName;
    private Integer modOrder;
    private String modDesc;
    private String modIsvalid;
    private Timestamp modUpdatedtime;
    private String modUpdatedby;
    private String modNameLocal;
    private String modDescLocal;
    private String modCategoryId;
    private String modUrl;
    
    
    private List<SmModule> childMdules;

    @Id
    @Column(name = "mod_code", nullable = false, length = 30)
    public String getModCode() {
        return modCode;
    }

    public void setModCode(String modCode) {
        this.modCode = modCode;
    }

    @Basic
    @Column(name = "mod_name", nullable = true, length = 60)
    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    @Basic
    @Column(name = "mod_order", nullable = true)
    public Integer getModOrder() {
        return modOrder;
    }

    public void setModOrder(Integer modOrder) {
        this.modOrder = modOrder;
    }

    @Basic
    @Column(name = "mod_desc", nullable = true, length = 120)
    public String getModDesc() {
        return modDesc;
    }

    public void setModDesc(String modDesc) {
        this.modDesc = modDesc;
    }

    @Basic
    @Column(name = "mod_isvalid", nullable = true, length = 1)
    public String getModIsvalid() {
        return modIsvalid;
    }

    public void setModIsvalid(String modIsvalid) {
        this.modIsvalid = modIsvalid;
    }

    @Basic
    @Column(name = "mod_updatedtime", nullable = true)
    public Timestamp getModUpdatedtime() {
        return modUpdatedtime;
    }

    public void setModUpdatedtime(Timestamp modUpdatedtime) {
        this.modUpdatedtime = modUpdatedtime;
    }

    @Basic
    @Column(name = "mod_updatedby", nullable = true, length = 255)
    public String getModUpdatedby() {
        return modUpdatedby;
    }

    public void setModUpdatedby(String modUpdatedby) {
        this.modUpdatedby = modUpdatedby;
    }

    @Basic
    @Column(name = "mod_name_local", nullable = true, length = 60)
    public String getModNameLocal() {
        return modNameLocal;
    }

    public void setModNameLocal(String modNameLocal) {
        this.modNameLocal = modNameLocal;
    }

    @Basic
    @Column(name = "mod_desc_local", nullable = true, length = 60)
    public String getModDescLocal() {
        return modDescLocal;
    }

    public void setModDescLocal(String modDescLocal) {
        this.modDescLocal = modDescLocal;
    }

    @Basic
    @Column(name = "mod_category_id", nullable = true, length = 128)
    public String getModCategoryId() {
        return modCategoryId;
    }

    public void setModCategoryId(String modCategoryId) {
        this.modCategoryId = modCategoryId;
    }

    @Basic
    @Column(name = "mod_url", nullable = true, length = 128)
    public String getModUrl() {
        return modUrl;
    }

    public void setModUrl(String modUrl) {
        this.modUrl = modUrl;
    }

    @Transient
    public List<SmModule> getChildMdules() {
        return childMdules;
    }

    public void setChildMdules(List<SmModule> childMdules) {
        this.childMdules = childMdules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmModule smModule = (SmModule) o;
        return Objects.equals(modCode, smModule.modCode) &&
                Objects.equals(modName, smModule.modName) &&
                Objects.equals(modOrder, smModule.modOrder) &&
                Objects.equals(modDesc, smModule.modDesc) &&
                Objects.equals(modIsvalid, smModule.modIsvalid) &&
                Objects.equals(modUpdatedtime, smModule.modUpdatedtime) &&
                Objects.equals(modUpdatedby, smModule.modUpdatedby) &&
                Objects.equals(modNameLocal, smModule.modNameLocal) &&
                Objects.equals(modDescLocal, smModule.modDescLocal) &&
                Objects.equals(modCategoryId, smModule.modCategoryId) &&
                Objects.equals(modUrl, smModule.modUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(modCode, modName, modOrder, modDesc, modIsvalid, modUpdatedtime, modUpdatedby, modNameLocal, modDescLocal, modCategoryId, modUrl);
    }

    @Override
    public String toString() {
        return "SmModule{" +
                "modCode='" + modCode + '\'' +
                ", modName='" + modName + '\'' +
                ", modOrder=" + modOrder +
                ", modDesc='" + modDesc + '\'' +
                ", modIsvalid='" + modIsvalid + '\'' +
                ", modUpdatedtime=" + modUpdatedtime +
                ", modUpdatedby='" + modUpdatedby + '\'' +
                ", modNameLocal='" + modNameLocal + '\'' +
                ", modDescLocal='" + modDescLocal + '\'' +
                ", modCategoryId='" + modCategoryId + '\'' +
                ", modUrl='" + modUrl + '\'' +
                ", childMdules=" + childMdules +
                '}';
    }
}
