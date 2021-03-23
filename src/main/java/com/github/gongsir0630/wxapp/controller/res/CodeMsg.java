package com.github.gongsir0630.wxapp.controller.res;

/**
 * @author 码之泪殇 GitHub: https://github.com/gongsir0630
 * @date 2021/3/20 18:47
 * 你的指尖,拥有改变世界的力量
 * 描述: code和msg封装类
 */
public class CodeMsg {
    private final int code;
    private final String msg;

    public static CodeMsg SUCCESS=new CodeMsg(0,"success");

    public static CodeMsg NO_CODE = new CodeMsg(-1,"js_code empty");

    public CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            '}';
    }

}
