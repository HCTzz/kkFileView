package com.ctt.exception;

import com.ctt.constant.SystemStatusEnum;
import com.ctt.response.WebResBean;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @auther Administrator
 * @create 2020-03-05 下午 3:17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public WebResBean httpMessageNotReadableException(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        return WebResBean.createResBean(SystemStatusEnum.E_503);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResBean httpRequestMethodHandler() {
        return WebResBean.createResBean(SystemStatusEnum.E_500);
    }

    @ExceptionHandler(value = Exception.class)
    public WebResBean defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        WebResBean rsb = WebResBean.createResBean(SystemStatusEnum.E_400);
        JSONObject errorObject = new JSONObject();
        logger.error("异常信息，错误位置【{}】", errorPosition);
        return rsb;
    }

}
