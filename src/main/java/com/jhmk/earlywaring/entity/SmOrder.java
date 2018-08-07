package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/8/2 19:56
 */

@Entity
@Table(name = "sm_order", schema = "jhmk_waring")
public class SmOrder {
    private int id;
    private Integer orderNum;
    private String orderName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_num", nullable = true)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "order_name", nullable = true, length = 255)
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmOrder smOrder = (SmOrder) o;
        return id == smOrder.id &&
                Objects.equals(orderNum, smOrder.orderNum) &&
                Objects.equals(orderName, smOrder.orderName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderNum, orderName);
    }
}
