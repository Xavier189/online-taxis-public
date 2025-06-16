package com.cola.internal.common;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Xavier
 * @date 2025/6/16
 * @description 数据传输对象所有的父类，包括命令、查询、相应
 */
public abstract class DTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 业务标识
     */
    private String bizId;

}
