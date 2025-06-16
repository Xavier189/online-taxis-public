package com.cola.common.dto;


import lombok.Data;

@Data
public class TokenResult {

    /**
     * 手机号 *
     */
    private String phone;

    /**
     *  身份标识 暂定1-乘客 2-司机*
     */
    private String identity;

}
