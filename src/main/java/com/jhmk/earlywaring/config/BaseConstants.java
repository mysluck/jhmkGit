package com.jhmk.earlywaring.config;

import org.springframework.stereotype.Component;


@Component
public class BaseConstants {

    public static final String USER_STATUS = "0";//用户状态 0-正常 1-异常
    public static final String CURRENT_ROLE_ID = "currentRoleId";
    public static final String CURRENT_ROLE_RANGE = "range";
    public static final String USER_ID = "userId";
    public static final String DEPT_ID = "deptId";
    public static final String DEPT_NAME = "deptName";
    public static final String TOKEN = "token";


    //参数状态：1-启用 0- 未启用
    public static final String PARAMTER_STATUS_1 = "1";//参数启用
    public static final String PARAMTER_STATUS_0 = "0";//参数未启用


    public static final String ALARM_CODE1 = "住院";//住院预警
    public static final String ALARM_CODE2 = "门诊";//住院预警


    public static final String OK = "200";// 状态码 200 成功
    public static final String SUCCESS = "success";
    public static final String FALSE = "false";

    //getVariableList获取变量列表
    public static final String getVariableList = "/med/cindecision/getVariableList.json";
    public static final String addrule = "/med/cindecision/addrule.json";
    public static final String findallnotsubmitrule = "/med/cindecision/findallnotsubmitrule.json";
    public static final String findallsubmitrule = "/med/cindecision/findallsubmitrule.json";
    public static final String findallrule = "/med/cindecision/findallrule.json";
    public static final String findrulebycondition = "/med/cindecision/findrulebycondition.json";
    public static final String submitrule = "/med/cindecision/submitrule.json";
    public static final String matchrule = "/med/cindecision/matchrule.json";
    public static final String updaterule = "/med/cindecision/updaterule.json";
    public static final String getruleforid = "/med/cindecision/getruleforid.json";//查询单条规则

    //改变预警等级
    public static final String changewarninglevel = "/med/cindecision/changewarninglevel.json";
    //审核规则
    public static final String examinerule = "/med/cindecision/examinerule.json";
    //是否运行规则
    public static final String isrunruler = "/med/cindecision/isrunruler.json";
    //专科标识
    public static final String changeIdentification = "/med/cindecision/changeIdentification.json";
    public static final String deleterule = "/med/cindecision/deleterule.json";

    public static final String statisticsrulercount = "/med/cindecision/statisticsrulercount.json";// 统计规则数目
    public static final String groupbyclassification = "/med/cindecision/groupbyclassification.json"; // 分组统计规则分类得数据
    public static final String groupbywarninglevel = "/med/cindecision/groupbywarninglevel.json"; // 分组统计预警等级数据
    public static final String groupbyidentification = "/med/cindecision/groupbyidentification.json";//  分组统计专科标识数据
    public static final String groupbycreatetime = "/med/cindecision/groupbycreatetime.json";// 传入 startDate  endDate 两个参数  按时间分组统计规则


    //医问道接口
    //3.高级检索值域变量（post）
    public static final String getfieldbyid = "/med/kninterface/getfieldbyid.json";
    //高级检索单位变量（post）
    public static final String getunitsbyid = "/med/kninterface/getunitsbyid.json";
    //5.高级检索根据关键字检索 拼音or汉字（post）
    public static final String searchbyvariablename = "/med/searchbyvariablename.json";
    //接口：获取字段的特殊类型
    public static final String getSpecialTypeByField = "/med/getSpecialTypeByField.json";
    // 获取最新列表
    public static final String getVariableListNew = "/med/getVariableListNew.json";
    //获取诊疗提醒
    public static final String getTipList = "/med/cdss/getTipList.json";


    //获取疾病同义词
    public static final String getSamilarWord = "/med/cdss/getSamilarWord.json";

    //获取疾病子节点
    public static final String getDiseaseChildrenList = "/med/cdss/getDiseaseChildrenList.json";
    //获取疾病父节点
    public static final String getParentList = "/med/cdss/getParentList.json";


    /**
     * OID账号，用于数据中心权限通行证
     */
    public static final String OID = "1.2.156.112636.1.2.46";
    /**
     * 数据中心webservice 服务接口
     */
    public static final String JHHDRWS001 = "JHHDRWS001";//患者信息
    public static final String JHHDRWS004A = "JHHDRWS004A";//入出转
    public static final String JHHDRWS005 = "JHHDRWS005";//检查数据
    public static final String JHHDRWS006A = "JHHDRWS006A";//检验数据（主表）
    public static final String JHHDRWS006B = "JHHDRWS006B";//检验结果明细
    public static final String JHHDRWS007A = "JHHDRWS007A";//门诊就诊
    public static final String JHHDRWS007C = "JHHDRWS007C";//门诊诊断
    public static final String JHHDRWS012A = "JHHDRWS012A";//住院医嘱
    public static final String JHHDRWS017 = "JHHDRWS017";//体征测量
    public static final String JHHDRWS020C = "JHHDRWS020C";//首页诊断
    public static final String JHHDRWS020A = "JHHDRWS020A";//病案首页
    public static final String JHHDRWS021D = "JHHDRWS021D";//病历诊断
    public static final String JHHDRWS021A = "JHHDRWS021A";//病历文书
    public static final String JHHDRWS021B = "JHHDRWS021B";//病历章节（反结构化）
    public static final String JHHDRWS029 = "JHHDRWS029";//规整后数据，包括主诉、现病史、既往史、过敏史等数据
    public static final String JHHDRWS031 = "JHHDRWS031";//重症ICU生命体征

}
