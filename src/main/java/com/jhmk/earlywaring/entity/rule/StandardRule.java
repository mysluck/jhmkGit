package com.jhmk.earlywaring.entity.rule;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author ziyu.zhou
 * @date 2018/8/3 10:27
 */

/**
 * 标准规则相关
 * ((诊断名称等于高血压/高血压1级/高血压2级and医嘱项名称等于索塔洛克/索塔洛克1/索塔洛克2)and(诊断名称等于痛风/恶心and诊断序号不等于1))
 */

public class StandardRule {

    //新文档迷行名称
    private String name;
    //关系
    private String sympol;
    //标准规则
    private String standardValue;
    //子规则
    private List<String> childValues;

    //标准规则+子规则
    private List<String> allValues;

    private String status;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSympol() {
        return sympol;
    }

    public void setSympol(String sympol) {
        this.sympol = sympol;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    public List<String> getChildValues() {
        return childValues;
    }

    public void setChildValues(List<String> childValues) {
        this.childValues = childValues;
    }

    public List<String> getAllValues() {
        return allValues;
    }

    public void setAllValues(List<String> allValues) {
        this.allValues = allValues;
    }


}
