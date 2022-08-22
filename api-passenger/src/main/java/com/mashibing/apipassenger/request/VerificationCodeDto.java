package com.mashibing.apipassenger.request;

import lombok.Data;

@Data
public class VerificationCodeDto {


    private String passengerPhone;

    private String verificationCode;


}
