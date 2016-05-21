package com.josie.quake.commons.utils;

/**
 * Created by Josie on 16/5/11.
 */
public enum ErrorInfo {

    //通用错误
    ARGUMENG_ERROR(100001, "参数错误"),
    UNKNOWN_ERROR(100000, "出了点问题,我们正在处理"),


    //没有权限
    NO_PRIVILEGE(200000, "不好意思,您没有权限操作"),
    NO_LOGUP(200001, "该用户尚未注册");

    private final int code;
    private final String msg;

    ErrorInfo(
            int code,
            String msg) {
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
