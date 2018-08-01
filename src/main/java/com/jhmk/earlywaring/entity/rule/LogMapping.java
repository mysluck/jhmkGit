package com.jhmk.earlywaring.entity.rule;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/31 20:16
 */

@Entity
@Table(name = "rule_log_mapping", schema = "jhmk_waring", catalog = "")
public class LogMapping {
    private int id;
    private int logId;
    private String logObj;
    private String logRelation;
    private String logResult;
    private String logTime;

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
                Objects.equals(logTime, that.logTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, logId, logObj, logRelation, logResult, logTime);
    }
}
