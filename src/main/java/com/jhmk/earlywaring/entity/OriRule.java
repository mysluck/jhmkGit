package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ori_rule", schema = "jhmk_waring")
public class OriRule {
    private String rule;
    private String mid;

    @Basic
    @Column(name = "rule", nullable = false)
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Id
    @Column(name = "mid", nullable = false)
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OriRule oriRule = (OriRule) o;
        return Objects.equals(rule, oriRule.rule) &&
                Objects.equals(mid, oriRule.mid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rule, mid);
    }
}
