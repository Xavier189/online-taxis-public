package com.cola.internal.common;

import com.cola.internal.exception.ErrorStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 响应父
 */
@Getter
@Setter
public class Response extends DTO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应是否成功
     */
    private boolean success;

    /**
     * 表示响应的错误代码
     * 用于标识具体的错误类型或状态
     */
    private int code;

    /**
     * 表示响应中与错误相关的描述信息
     * 通常用于提供更加友好或详细的错误提示
     */
    private String message;

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(1);
        response.setMessage("success");
        return response;
    }

    public static Response buildFailure(int errorCode, String errorMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static Response buildFailure(ErrorStatus status) {
        return buildFailure(status.getCode(), status.getMessage());
    }

//    public static Response buildFailure(int errorCode, String errorMessage, String serviceHost) {
//        Response response = buildFailure(errorCode, errorMessage);
//        response.setServiceHost(serviceHost);
//        return response;
//    }
//
//    public static Response buildFailure(ErrorStatus status, String serviceHost) {
//        Response response = buildFailure(status.getCode(), status.getMessage());
//        response.setServiceHost(serviceHost);
//        return response;
//    }


    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", errorCode=" + code +
                ", errorMessage='" + message + '\'' +
                '}';
    }
}
