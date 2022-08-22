package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.request.VerificationCodeDto;
import com.mashibing.apipassenger.service.VerificationCodeService;
import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {



    @Autowired
    VerificationCodeService verificationCodeService;



    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDto verificationCodeDto){

        String passengerPhone = verificationCodeDto.getPassengerPhone();
        System.out.println("接收到的手机号参数是：" + passengerPhone);


        return verificationCodeService.generatorCode(passengerPhone);

    }


    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDto verificationCodeDto){

        String passengerPhone = verificationCodeDto.getPassengerPhone();
        String verificationCode = verificationCodeDto.getVerificationCode();

        System.out.println("手机号：" + passengerPhone + "，验证码：" + verificationCode);

        return verificationCodeService.checkCode(passengerPhone,verificationCode);


    }


}
