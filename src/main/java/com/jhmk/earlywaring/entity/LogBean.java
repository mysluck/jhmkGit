package com.jhmk.earlywaring.entity;

/**
 * 临床预警界面科室人员分布bean
 */
public class LogBean {
    private String deptId;
    private String deptName;
    private String doctorId;
    private String doctorName;
    private int blue;
    private int yellow;
    private int orange;
    private int red;
    private String time;
    private int count;

    public LogBean() {
    }



    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getOrange() {
        return orange;
    }

    public void setOrange(int orange) {
        this.orange = orange;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", blue=" + blue +
                ", yellow=" + yellow +
                ", orange=" + orange +
                ", red=" + red +
                ", time='" + time + '\'' +
                ", count=" + count +
                '}';
    }
}
