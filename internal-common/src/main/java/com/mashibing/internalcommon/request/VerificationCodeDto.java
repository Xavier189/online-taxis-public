package com.mashibing.internalcommon.request;

import lombok.Data;

@Data
public class VerificationCodeDto {


    /**
     * 手机号 *
     */
    private String passengerPhone;

    /**
     * 验证码 *
     */
    private String verificationCode;


}
