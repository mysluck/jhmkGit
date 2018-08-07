package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/31 20:16
 */

@Entity
@Table(name = "rule_log_mapping", schema = "jhmk_waring")
public class LogMapping {
    private int id;
    private int logId;
    private String logObj;
    private String logRelation;
    private String logResult;
    private String logTime;
    private Integer logOrderF;
    private Integer logOrderS;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_id", nullable = false)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "log_obj", nullable = true, length = 50)
    public String getLogObj() {
        return logObj;
    }

    public void setLogObj(String logObj) {
        this.logObj = logObj;
    }

    @Basic
    @Column(name = "log_relation", nullable = true, length = 50)
    public String getLogRelation() {
        return logRelation;
    }

    public void setLogRelation(String logRelation) {
        this.logRelation = logRelation;
    }

    @Basic
    @Column(name = "log_result", nullable = true, length = 50)
    public String getLogResult() {
        return logResult;
    }

    public void setLogResult(String logResult) {
        this.logResult = logResult;
    }

    @Basic
    @Column(name = "log_time", nullable = true, length = 50)
    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
    @Basic
    @Column(name = "log_order_f", nullable = true)
    public Integer getLogOrderF() {
        return logOrderF;
    }

    public void setLogOrderF(Integer logOrderF) {
        this.logOrderF = logOrderF;
    }

    @Basic
    @Column(name = "log_order_s", nullable = true)
    public Integer getLogOrderS() {
        return logOrderS;
    }

    public void setLogOrderS(Integer logOrderS) {
        this.logOrderS = logOrderS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogMapping that = (LogMapping) o;
        return id == that.id &&
                logId == that.logId &&
                Objects.equals(logObj, that.logObj) &&
                Objects.equals(logRelation, that.logRelation) &&
                Objects.equals(logResult, that.logResult) &&
                Objects.equals(logTime, that.logTime) &&
                Objects.equals(logOrderF, that.logOrderF) &&
                Objects.equals(logOrderS, that.logOrderS);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, logId, logObj, logRelation, logResult, logTime, logOrderF, logOrderS);
    }

    @Override
    public String toString() {
        return "LogMapping{" +
                "id=" + id +
                ", logId=" + logId +
                ", logObj='" + logObj + '\'' +
                ", logRelation='" + logRelation + '\'' +
                ", logResult='" + logResult + '\'' +
                ", logTime='" + logTime + '\'' +
                ", logOrderF=" + logOrderF +
                ", logOrderS=" + logOrderS +
                '}';
    }
}
