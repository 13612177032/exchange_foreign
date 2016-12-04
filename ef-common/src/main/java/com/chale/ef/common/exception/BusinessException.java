package com.chale.ef.common.exception;


import org.apache.commons.lang.StringUtils;

/**
 * Created by gaoye1 on 2016/8/24.
 */
public class BusinessException extends Exception{
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }




    public BusinessException(ExceptionEmnu emnu) {
        this.code=emnu.getCode();
        this.message=emnu.getMsg();
    }

    public BusinessException(ExceptionEmnu emnu,String msg) {
        this.code=emnu.getCode();
        this.message=emnu.getMsg()+ (StringUtils.isBlank(msg)?"":(":"+msg));
    }
}
