//package com.jhmk.earlywaring.entity;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "sm_mapping", schema = "jhmk_waring")
//public class SmMapping {
//    private int id;
//    private String key;
//    private String value;
//
//    @Id
//    @Column(name = "id", nullable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "key", nullable = true, length = 10)
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    @Basic
//    @Column(name = "value", nullable = true, length = 10)
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SmMapping smMapping = (SmMapping) o;
//        return id == smMapping.id &&
//                Objects.equals(key, smMapping.key) &&
//                Objects.equals(value, smMapping.value);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(id, key, value);
//    }
//}
