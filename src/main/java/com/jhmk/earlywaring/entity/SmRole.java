package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sm_role", schema = "jhmk_waring", catalog = "")
public class SmRole {
    private String roleId;
    private String roleName;
    private String roleIsvalid;
    private String roleDesc;
    private Timestamp roleUpdatedtime;
    private String roleUpdatedby;
    private String roleNameLocal;
    private String roleDescLocal;
    private Long roleLevel;
    private String bizRoleFlag;
    private String orgId;
    private String ruleRange;

    public SmRole() {
    }

    public SmRole(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Id
    @Column(name = "role_id", nullable = false, length = 10)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_isvalid", nullable = false, length = 1)
    public String getRoleIsvalid() {
        return roleIsvalid;
    }

    public void setRoleIsvalid(String roleIsvalid) {
        this.roleIsvalid = roleIsvalid;
    }

    @Basic
    @Column(name = "role_desc", nullable = true, length = 1024)
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "role_updatedtime", nullable = true)
    public Timestamp getRoleUpdatedtime() {
        return roleUpdatedtime;
    }

    public void setRoleUpdatedtime(Timestamp roleUpdatedtime) {
        this.roleUpdatedtime = roleUpdatedtime;
    }

    @Basic
    @Column(name = "role_updatedby", nullable = true, length = 128)
    public String getRoleUpdatedby() {
        return roleUpdatedby;
    }

    public void setRoleUpdatedby(String roleUpdatedby) {
        this.roleUpdatedby = roleUpdatedby;
    }

    @Basic
    @Column(name = "role_name_local", nullable = true, length = 512)
    public String getRoleNameLocal() {
        return roleNameLocal;
    }

    public void setRoleNameLocal(String roleNameLocal) {
        this.roleNameLocal = roleNameLocal;
    }

    @Basic
    @Column(name = "role_desc_local", nullable = true, length = 1024)
    public String getRoleDescLocal() {
        return roleDescLocal;
    }

    public void setRoleDescLocal(String roleDescLocal) {
        this.roleDescLocal = roleDescLocal;
    }

    @Basic
    @Column(name = "role_level", nullable = true)
    public Long getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Long roleLevel) {
        this.roleLevel = roleLevel;
    }

    @Basic
    @Column(name = "biz_role_flag", nullable = true, length = 1)
    public String getBizRoleFlag() {
        return bizRoleFlag;
    }

    public void setBizRoleFlag(String bizRoleFlag) {
        this.bizRoleFlag = bizRoleFlag;
    }

    @Basic
    @Column(name = "org_id", nullable = true, length = 10)
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmRole that = (SmRole) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(roleIsvalid, that.roleIsvalid) &&
                Objects.equals(roleDesc, that.roleDesc) &&
                Objects.equals(roleUpdatedtime, that.roleUpdatedtime) &&
                Objects.equals(roleUpdatedby, that.roleUpdatedby) &&
                Objects.equals(roleNameLocal, that.roleNameLocal) &&
                Objects.equals(roleDescLocal, that.roleDescLocal) &&
                Objects.equals(roleLevel, that.roleLevel) &&
                Objects.equals(bizRoleFlag, that.bizRoleFlag) &&
                Objects.equals(orgId, that.orgId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleName, roleIsvalid, roleDesc, roleUpdatedtime, roleUpdatedby, roleNameLocal, roleDescLocal, roleLevel, bizRoleFlag, orgId);
    }

    @Override
    public String toString() {
        return "SmRole{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleIsvalid='" + roleIsvalid + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", roleUpdatedtime=" + roleUpdatedtime +
                ", roleUpdatedby='" + roleUpdatedby + '\'' +
                ", roleNameLocal='" + roleNameLocal + '\'' +
                ", roleDescLocal='" + roleDescLocal + '\'' +
                ", roleLevel=" + roleLevel +
                ", bizRoleFlag='" + bizRoleFlag + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }

    @Basic
    @Column(name = "rule_range", nullable = true, length = 1)
    public String getRuleRange() {
        return ruleRange;
    }

    public void setRuleRange(String ruleRange) {
        this.ruleRange = ruleRange;
    }
}
