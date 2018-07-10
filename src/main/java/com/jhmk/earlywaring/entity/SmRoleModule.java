package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sm_role_module", schema = "jhmk_waring")
public class SmRoleModule implements Serializable{
    private Integer id;
    private String roleId;
    private String moduleId;


    @Basic
    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id", nullable = false, length = 32)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "module_id", nullable = false, length = 32)
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmRoleModule that = (SmRoleModule) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, moduleId);
    }
}
