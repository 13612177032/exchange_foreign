package com.chale.ef.common.bean;

import com.chale.ef.common.exception.ExceptionEmnu;

/**
 * Created by liangchaolei on 2016/8/29.
 */
public class BaseResponse {

    private String code;
    private String msg;

    public BaseResponse() {
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(ExceptionEmnu enums) {
        this.code = String.valueOf(enums.getCode());
        this.msg = String.valueOf(enums.getMsg());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
