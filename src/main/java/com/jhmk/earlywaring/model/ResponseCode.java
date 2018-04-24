package com.jhmk.earlywaring.model;

/**
 * @author  yxy on 16/3/5.
 * 统一返回码
 * 规则：
 * 大于等于1的为正确的返回
 * 小于等于0的为错误的返回
 * 业务上错误码定义为5位
 */
public enum ResponseCode {

    /**
     * 默认的成功操作状态码
     */
    OK(10000,"成功"),
    /**
     * 默认的失败操作状态码
     */
    COMERROR(0,"其他错误"),

    /**
     * 系统错误
     */
    TIMEOUT(-400,"请求超时"),

    INERERROR(500,"internal error."),

    INERERROR1(501,"该角色正被使用，不能删除."),

    INERERROR2(502,"密码错误，请重新输入."),

    ADDRERROR(650,"添加失败,请重新添加"),

    ROLEDELETEERROR(-500,"请先删除此角色关联的菜单权限与用户"),



    NODATA(900,"data is empty"),

    /**
     *业务相关错误
     * 1开头为授权相关错误码
     */
    AUTH_ERROR(-10000, "授权错误"),

    /**
     * 2开头为额度相关错误
     */
    CREDIT_LMT_ERROR(-20000, "调额失败"),

    /**
     * 3开头为交易账户创建相关错误
     */
    TXN_ACCT_INIT_ERROR(-30000, "交易账户创建失败"),

    /**
     * 4开头为还款处理相关错误
     */
    PAYMENT_DEALING_ERORR(-40000,"还款处理失败"),

    /**
     * 5开头为账单处理相关错误
     */
    STMT_DEALING_ERROR(-50000, "账单处理失败"),

    /**
     * 6开头为延滞处理相关错误
     */
    DELQ_DEALING_ERROR(-60000, "延滞处理失败"),

    /**
     * 7开头为利息处理相关错误
     */
    INTR_DEALING_ERROR(-70000, "利息处理错误"),

    /**
     * 8开头规则、参数相关错误
     */
    RM_RULE_ERROR(-80000, "规则添加失败"),
    RM_RULE_CLASSNOTFOUND(-80001,"生成规则文件失败，类加载失败"),
    RM_RULE_BATCH_DEPLOY_ERROR(-80002,"批量部署规则脚本失败"),
    RM_RULESET_ERROR(-80003,"规则集添加失败,该产品已有该规则集"),
    RM_PROD_INFO_ERROR(-80004,"该机构下已有相同产品"),
    RM_SUMMON_ERROR(-80003,"核算规则类型添加失败,已有该核算类型"),
    /**
     * 9开头账务核算相关错误
     */

    CUSTOMER_NOT_EXIST(-11000, "客户号不存在")

;



    public int code;
    public String msg;

    ResponseCode(int c,String msg){
        this.code = c;
        this.msg = msg;
    }
}
