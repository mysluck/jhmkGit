package com.jhmk.earlywaring.model;

/**
 * @author  zzy
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
    OK(200,"成功"),
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

    INERERROR2(502,"用户或密码错误，请重新输入."),
    INERERROR3(503,"用户未登录，请重新登录."),
    INERERROR4(504,"规则匹配失败."),

    ADDRERROR(650,"添加失败,请重新添加"),

    ROLEDELETEERROR(-500,"请先删除此角色关联的菜单权限与用户"),



    NODATA(900,"data is empty"),



    CUSTOMER_NOT_EXIST(-11000, "客户号不存在");



    public int code;
    public String msg;

    ResponseCode(int c,String msg){
        this.code = c;
        this.msg = msg;
    }
}
