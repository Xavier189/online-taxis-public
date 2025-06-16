package com.cola.internal.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 单信息响应
 */
@Getter
@Setter
public class SingleResponse<T> extends Response {

    @Serial
    private static final long serialVersionUID = 1L;

    private T data;

    public static SingleResponse<Void> buildSuccess() {
        SingleResponse<Void> response = new SingleResponse<>();
        response.setSuccess(true);
        return response;
    }

    public static SingleResponse<Void> buildFailure(int errorCode, String errorMessage) {
        SingleResponse<Void> response = new SingleResponse<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

}
