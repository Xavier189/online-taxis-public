package com.cola.internal.exception;

import lombok.Getter;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 异常枚举
 */
public enum ErrorStatus {

    // 2001-: 非功能性异常

    // 3001->: 资源异常
    RESOURCE_NOT_FOUND(3001, "资源不存在"),
    // 3002: 资源已存在异常
    RESOURCE_ALREADY_EXIST(3002, "资源已存在"),
    // 3003: 资源已被占用异常
    RESOURCE_IN_USE(3003, "资源已被占用"),
    // 3004: 资源不可用异常
    RESOURCE_UNAVAILABLE(3004, "资源不可用"),
    // 3005: 资源不可用异常
    RESOURCE_INVALID(3005, "资源无效"),

    // 9001-: 系统级异常
    SYSTEM_ERROR(9999, "未预期的错误，请联系系统负责人");

    @Getter
    private final int code;
    @Getter
    private String message;

    ErrorStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorStatus addMessage(String msg) {
        this.message = this.message + "," + msg;
        return this;
    }


}
