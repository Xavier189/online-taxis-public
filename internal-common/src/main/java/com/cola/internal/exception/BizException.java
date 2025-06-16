package com.cola.internal.exception;

import java.io.Serial;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 已知异常
 */
public class BizException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BizException(int errorCode, String message) {
        super(errorCode, message);
    }

    public BizException(ErrorStatus errorStatus) {
        super(errorStatus);
    }

    public BizException(ErrorStatus errorStatus, String newMsg) {
        super(errorStatus.addMessage(newMsg));
    }
}
