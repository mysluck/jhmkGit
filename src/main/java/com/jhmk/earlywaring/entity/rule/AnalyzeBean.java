package com.jhmk.earlywaring.entity.rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 添加规则时  用于解析的实体类
 */
public class AnalyzeBean implements Serializable{
    private String unit;
    private String flag;
    private String field;
    private String values;
    private String exp;

    /**
     * 以下字段为接受已有的mongo库的规则 ，解析为原前台传过来的字段，方便前台重新编辑规则信息
     */
    private List<String> value=new LinkedList<>();//等同于values
    private String umType;// suerModel 的 类型





    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public String getUmType() {
        return umType;
    }

    public void setUmType(String umType) {
        this.umType = umType;
    }
}
