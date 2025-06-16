package com.cola.internal.exception;

import java.io.Serial;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 系统内部异常
 */
public class SysException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public SysException(int errorCode, String message) {
        super(errorCode, message);
    }

    public SysException(ErrorStatus errorStatus) {
        super(errorStatus);
    }

    public SysException(ErrorStatus errorStatus, String newMsg) {
        super(errorStatus.addMessage(newMsg));
    }
}
