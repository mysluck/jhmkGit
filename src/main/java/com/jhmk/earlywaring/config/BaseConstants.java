package com.jhmk.earlywaring.config;

import org.springframework.stereotype.Component;


@Component
public class BaseConstants {

    public static final String USER_STATUS = "0";//用户状态 0-正常 1-异常
    public static final String CURRENT_ROLE_ID = "currentRoleId";
    public static final String USER_ID = "userId";
    public static final String DEPT_ID = "deptId";
    public static final String DEPT_NAME = "deptName";
    public static final String TOKEN = "token";


    public static final String DEFAULT_ORG = "0001";//默认业务机构
    public static final String REMIT_SELF_BANK = "0";//bank_type放款本行二类户
    public static final String REMIT_OTHER_BANK = "1";//bank_type放款他行借记卡

    //参数状态：1-启用 0- 未启用
    public static final String PARAMTER_STATUS_1 = "1";//参数启用
    public static final String PARAMTER_STATUS_0 = "0";//参数未启用


    public static final String ALARM_CODE1 = "住院";//住院预警
    public static final String ALARM_CODE2 = "门诊";//住院预警


    public static final String ALARMSTSTUS1 = "1";//触发预警
    public static final String ALARMSTSTUS0 = "0";//没触发预警

    public static final String TYPECLASS = "typeClass";


    public static final String OK = "200";// 状态码 200 成功
    public static final String SUCCESS = "success";
    public static final String FALSE = "false";

    public static final String DATASOURCES = "BYSYDATA";
    public static final String BINGANSHOUYE = "binganshouye";
    public static final String BINGLIZHENDUAN = "binglizhenduan";
    public static final String SHOUYEZHENDUAN = "shouyezhenduan";
    public static final String RUYUANJILU = "ruyuanjilu";





    //getVariableList获取变量列表
    public static final String getVariableList = "/med/cindecision/getVariableList.json";
    public static final String addrule = "/med/cindecision/addrule.json";
    public static final String findallnotsubmitrule = "/med/cindecision/findallnotsubmitrule.json";
    public static final String findallsubmitrule = "/med/cindecision/findallsubmitrule.json";
    public static final String findallrule = "/med/cindecision/findallrule.json";
    public static final String submitrule = "/med/cindecision/submitrule.json";
    public static final String matchrule = "/med/cindecision/matchrule.json";
    public static final String updaterule = "/med/cindecision/updaterule.json";

    //改变预警等级
    public static final String changewarninglevel = "/med/cindecision/changewarninglevel.json";
    //审核规则
    public static final String examinerule = "/med/cindecision/examinerule.json";
    //是否运行规则
    public static final String isrunruler = "/med/cindecision/isrunruler.json";
    //专科标识
    public static final String changeIdentification = "/med/cindecision/changeIdentification.json";
    public static final String deleterule = "/med/cindecision/deleterule.json";


    //医问道接口
    //3.高级检索值域变量（post）
    public static final String getfieldbyid = "/med/getfieldbyid.json";
    //高级检索单位变量（post）
    public static final String getunitsbyid = "/med/getunitsbyid.json";
    //5.高级检索根据关键字检索 拼音or汉字（post）
    public static final String searchbyvariablename = "/med/searchbyvariablename.json";
    //接口：获取字段的特殊类型
    public static final String getSpecialTypeByField = "/med/getSpecialTypeByField.json";
    // 获取最新列表
    public static final String getVariableListNew = "/med/getVariableListNew.json";

}
