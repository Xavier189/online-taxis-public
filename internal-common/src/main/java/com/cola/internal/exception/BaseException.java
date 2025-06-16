package com.cola.internal.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 基础异常抽象类
 */
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int errorCode;

    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorCode = errorStatus.getCode();
    }
}
