package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_data_model_mapping", schema = "jhmk_waring")
public class UserDataModelMapping {
    private int udmmId;
    private String umNamePath;
    private String dmNamePath;
    private String dmTypePath;

    @Id
    @Column(name = "udmm_id", nullable = false)
    public int getUdmmId() {
        return udmmId;
    }

    public void setUdmmId(int udmmId) {
        this.udmmId = udmmId;
    }

    @Basic
    @Column(name = "um_name_path", nullable = true, length = 4096)
    public String getUmNamePath() {
        return umNamePath;
    }

    public void setUmNamePath(String umNamePath) {
        this.umNamePath = umNamePath;
    }

    @Basic
    @Column(name = "dm_name_path", nullable = true, length = 4096)
    public String getDmNamePath() {
        return dmNamePath;
    }

    public void setDmNamePath(String dmNamePath) {
        this.dmNamePath = dmNamePath;
    }

    @Basic
    @Column(name = "dm_type_path", nullable = true, length = 4096)
    public String getDmTypePath() {
        return dmTypePath;
    }

    public void setDmTypePath(String dmTypePath) {
        this.dmTypePath = dmTypePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDataModelMapping that = (UserDataModelMapping) o;
        return udmmId == that.udmmId &&
                Objects.equals(umNamePath, that.umNamePath) &&
                Objects.equals(dmNamePath, that.dmNamePath) &&
                Objects.equals(dmTypePath, that.dmTypePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(udmmId, umNamePath, dmNamePath, dmTypePath);
    }
}
