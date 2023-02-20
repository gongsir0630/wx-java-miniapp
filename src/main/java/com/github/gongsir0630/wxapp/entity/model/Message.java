package com.github.gongsir0630.wxapp.entity.model;

import org.apache.commons.lang3.StringUtils;

import com.github.gongsir0630.wxapp.enums.ResultCode;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
public class Message<T> {
    private int code;
    private String msg;
    private T data;

    private Message(ResultCode resultCode, T data){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    private Message(ResultCode resultCode, T data, String msg){
        this(resultCode, data);
        this.msg = StringUtils.firstNonBlank(msg, this.msg);
    }


    /**
     * 成功时
     * @param <T> data泛型
     * @return Message
     */
    public static <T> Message<T> success(T data){
        return new Message<>(ResultCode.SUCCESS, data);
    }

    /**
     * 失败
     * @param <T> data泛型
     * @return Message
     */
    public static <T> Message<T> fail(ResultCode resultCode){
        return new Message<>(resultCode, null);
    }

    public static <T> Message<T> fail(ResultCode resultCode, String msg){
        return new Message<>(resultCode, null, msg);
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

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
