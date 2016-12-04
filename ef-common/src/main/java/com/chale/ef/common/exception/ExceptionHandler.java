package com.chale.ef.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.chale.ef.common.bean.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liangcl on 2016/12/3.
 */
public class ExceptionHandler  extends AbstractHandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("ExceptionHandler.doResolveException", e);
        BusinessException be;
        if (e instanceof  BusinessException) {
            be= (BusinessException) e;
        }else{
            be= new BusinessException(ExceptionEmnu.SYSTEM_EXCEPTION);
        }
        BaseResponse br=new BaseResponse(String.valueOf(be.getCode()),be.getMessage());

        ModelAndView mv = new ModelAndView();             
                    /*  使用response返回    */
        httpServletResponse.setStatus(HttpStatus.OK.value()); //设置状态码
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        httpServletResponse.setCharacterEncoding("UTF-8"); //避免乱码
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            httpServletResponse.getWriter().write(JSONObject.toJSONString(br));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return mv;
    }
}
