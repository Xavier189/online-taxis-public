package com.cola.apipassenger.config;

import com.cola.internal.common.Response;
import com.cola.internal.exception.BizException;
import com.cola.internal.exception.ErrorStatus;
import com.cola.internal.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2025/6/18
 * @description 全局异常捕获器
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Value("${service_host:api-passenger-8081}")
    private String serviceHost;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BizException.class)
    public Response handleExceptions(BizException ex) {
        log.error("已知异常", ex);
        return Response.buildFailure(ex.getErrorCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SysException.class)
    public Response handleSysExceptions(SysException ex) {
        log.error("系统级异常", ex);
        return Response.buildFailure(ex.getErrorCode(), ex.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.error("参数校验失败:{}", errorMessage, ex);
        if (StringUtils.isBlank(errorMessage))
            return Response.buildFailure(ErrorStatus.PARAMETER_ERROR);
        else return Response.buildFailure(ErrorStatus.PARAMETER_ERROR.getCode(), errorMessage);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleAllExceptions(Exception e) {
        log.error("系统出现未预期的错误", e);
        return Response.buildFailure(ErrorStatus.SYSTEM_ERROR);
    }

}
