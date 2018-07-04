package com.jhmk.earlywaring.entity.rule;

import java.util.Date;
import java.util.Map;

//触发诊断预警规则后 提示 高钾血症 当前 钾 100 请确认诊断名
public class HintMesRule {
    //主诊断
    private String mainIllName;
    //其他诊断
    private String otherIllName;
    //其他检验检查项
    private Map<String, String> otherMap;
    private String sex;
    private String yizhu;
    private String date;

    public String getMainIllName() {
        return mainIllName;
    }

    public void setMainIllName(String mainIllName) {
        this.mainIllName = mainIllName;
    }

    public String getOtherIllName() {
        return otherIllName;
    }

    public void setOtherIllName(String otherIllName) {
        this.otherIllName = otherIllName;
    }

    public Map<String, String> getOtherMap() {
        return otherMap;
    }

    public void setOtherMap(Map<String, String> otherMap) {
        this.otherMap = otherMap;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYizhu() {
        return yizhu;
    }

    public void setYizhu(String yizhu) {
        this.yizhu = yizhu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
