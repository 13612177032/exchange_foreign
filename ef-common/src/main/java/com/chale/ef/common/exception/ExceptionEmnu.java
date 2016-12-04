package com.chale.ef.common.exception;

/**
 * Created by gaoye1 on 2016/8/24.
 */
public enum ExceptionEmnu {


    SUCCESS(1000, "成功"),
    CHECK_FAIL(1001, "验证失败"),
    RECALL_FAIL(1002, "撤销失败"),
    NULL(1003, "空值异常"),
    MONEY_NOT_EQ(1004, "金额计算不正确，请验证"),
    NOT_SUPPORT_CURRENCY(1005, "不支持该币种"),
    NOT_SUPPORT_EXCHANGE_TYPE(1006, "不支持该交易类型"),
    SYSTEM_EXCEPTION(9999, "系统异常");




    private Integer code;
    private String msg;

    ExceptionEmnu(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
