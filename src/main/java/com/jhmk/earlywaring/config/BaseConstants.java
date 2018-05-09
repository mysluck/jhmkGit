package com.jhmk.earlywaring.config;

public class BaseConstants {


    public static final String USER_STATUS = "0";//用户状态 0-正常 1-异常
    public static final String CURRENT_ROLE_ID = "currentRoleId";
    public static final String USER_ID = "userId";
    public static final String DEPT_ID = "deptId";
    public static final String FT_DEPT_ID = "ftdeptId";
    public static final String FT_DEPT_NAME = "ftdeptName";
    public static final String TOKEN = "token";


    public static final String DEFAULT_ORG = "0001";//默认业务机构
    public static final String REMIT_SELF_BANK = "0";//bank_type放款本行二类户
    public static final String REMIT_OTHER_BANK = "1";//bank_type放款他行借记卡

    //参数状态：1-启用 0- 未启用
    public static final String PARAMTER_STATUS_1 = "1";//参数启用
    public static final String PARAMTER_STATUS_0 = "0";//参数未启用


    public static final String ALARM_CODE1 = "1";//科室预警
    public static final String ALARM_CODE2 = "2";//住院预警


    public static final String ALARMSTSTUS1 = "1";//触发预警
    public static final String ALARMSTSTUS0 = "0";//没触发预警

    public static final String OK = "200";// 状态码 200 成功
    public static final String SUCCESS = "success";
    public static final String FALSE = "false";

    public static final String DATASOURCES = "BYSYDATA";
    public static final String BINGANSHOUYE = "binganshouye";
    public static final String BINGLIZHENDUAN = "binglizhenduan";
    public static final String SHOUYEZHENDUAN = "shouyezhenduan";
    public static final String RUYUANJILU = "ruyuanjilu";

    //getVariableList获取变量列表
    public static final String getVariableList = "http://192.168.8.20:8820/demo/med/cindecision/getVariableList.json";
    public static final String addrule = "http://192.168.8.20:8820/demo/med/cindecision/addrule.json";
    public static final String findallnotsubmitrule = "http://192.168.8.20:8820/demo/med/cindecision/findallnotsubmitrule.json";
    public static final String findallsubmitrule = "http://192.168.8.20:8820/demo/med/cindecision/findallsubmitrule.json";
    public static final String submitrule = "http://192.168.8.20:8820/demo/med/cindecision/submitrule.json";
//    public static final String addrule = "http://192.168.8.20:8820/demo/med/cindecision/addrule.json";


}
