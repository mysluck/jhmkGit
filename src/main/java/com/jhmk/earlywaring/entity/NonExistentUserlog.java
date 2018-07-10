package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "non_existent_userlog", schema = "jhmk_waring")
public class NonExistentUserlog {
    private int id;
    private String userId;

    @Basic
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NonExistentUserlog that = (NonExistentUserlog) o;
        return id == that.id &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId);
    }
}
