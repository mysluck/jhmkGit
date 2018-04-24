package com.jhmk.earlywaring.model;

import java.io.Serializable;

/**
 * @author  yxy on 16/3/5.
 */
public class AtResponse<T> implements Serializable{

    private long respTime;
    private T data;
    private int code;
    private String message;

    private Object dataObject;
    public Object getDataObject() {
        return dataObject;
    }
    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }


    public AtResponse(ResponseCode responseCode){
        this(System.currentTimeMillis());
        this.code = responseCode.code;
        this.message = responseCode.msg;
    }

    public AtResponse(long respTime) {
        this.respTime = respTime;
    }

    public AtResponse(){

    }

    public long getRespTime() {
        return respTime;
    }

    public void setRespTime(long respTime) {
        this.respTime = respTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message){
    	this.message = message;
    }

    public void setResponseCode(ResponseCode respCode){
        this.message = respCode.msg;
        this.code = respCode.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AtResponse<?> that = (AtResponse<?>) o;

        if (respTime != that.respTime) {
            return false;
        }
        return code == that.code;

    }

    @Override
    public int hashCode() {
        int result = (int) (respTime ^ (respTime >>> 32));
        result = 31 * result + code;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AtResponse{");
        sb.append("respTime=").append(respTime);
        sb.append(", data=").append(data);
        sb.append(", code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
