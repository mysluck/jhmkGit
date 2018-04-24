package com.jhmk.earlywaring.model;

/**
 * list页面 增、删、改、查是否可操作
 *
 * @author niqinqin
 */
public class OperateBean {
    public final static String CREATE = "create";
    public final static String VIEW = "view";
    public final static String EDIT = "edit";
    public final static String DELETE = "delete";
    public final static String AUTH = "auth";

    /**
     * true 表示可显示新增链接，false表示不可显示
     */
    private boolean create = false;
    /**
     * 查看链接
     */
    private boolean view = false;
    /**
     * 编辑链接
     */
    private boolean edit = false;
    /**
     * 删除链接
     */
    private boolean delete = false;
    /**
     * 权限链接
     */
    private boolean auth = false;

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isCreate() {
        return create;
    }

    public boolean isView() {
        return view;
    }

    public boolean isEdit() {
        return edit;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }


}
