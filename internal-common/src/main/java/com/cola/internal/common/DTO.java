package com.cola.internal.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 数据传输对象所有的父类，包括命令、查询、相应
 */
@Getter
@Setter
public abstract class DTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 业务标识
     */
    //private String bizId;

    /**
     * 表示服务主机地址。
     *
     * 用于指定或记录服务所在的主机，用于对服务的访问管理。
     */
    //private String serviceHost;

}
