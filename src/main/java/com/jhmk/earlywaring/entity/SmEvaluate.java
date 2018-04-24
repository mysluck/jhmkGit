package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 用户评价表
 */
@Entity
@Table(name = "sm_evaluate", schema = "jhmk_waring")
public class SmEvaluate implements Serializable{
    private int id;
    private String userId;
    private String evaluate;
    private String suggest;
    private String status;
    private Timestamp createDate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 100)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "evaluate", nullable = true, length = 3)
    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Basic
    @Column(name = "suggest", nullable = true, length = 255)
    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 3)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_date", nullable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmEvaluate that = (SmEvaluate) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(evaluate, that.evaluate) &&
                Objects.equals(suggest, that.suggest) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, evaluate, suggest, status, createDate);
    }
}
