package com.jhmk.earlywaring.entity.rule;

import java.io.Serializable;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 9:56
 */

public class Jianchabaogao implements Serializable{
    private String exam_no;//检查号
    private String exam_item_name;//检查名称
    private String exam_time;//检查结果产生时间
    private String exam_diag;//检查结果
    private String exam_feature;//医师所见

    public String getExam_no() {
        return exam_no;
    }

    public void setExam_no(String exam_no) {
        this.exam_no = exam_no;
    }

    public String getExam_item_name() {
        return exam_item_name;
    }

    public void setExam_item_name(String exam_item_name) {
        this.exam_item_name = exam_item_name;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getExam_diag() {
        return exam_diag;
    }

    public void setExam_diag(String exam_diag) {
        this.exam_diag = exam_diag;
    }

    public String getExam_feature() {
        return exam_feature;
    }

    public void setExam_feature(String exam_feature) {
        this.exam_feature = exam_feature;
    }
}
