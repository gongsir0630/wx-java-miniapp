package com.github.gongsir0630.wxapp.enums;

/**
 * @author Kyle <gongsir0630@gmail.com>
 * Created on 2023/02/02
 */
public enum ResultCode {
    SUCCESS(0, "success"),
    NO_CODE(-1, "js_code is empty")
    ;

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
