package com.cola.apidriver.controller;


import com.cola.apidriver.service.VerificationCodeService;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {


    @Autowired
    private VerificationCodeService verificationCodeService;


    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDto){

        String driverPhone = verificationCodeDto.getDriverPhone();
        System.out.println("接收到的手机号参数是：" + driverPhone);


        return verificationCodeService.checkAndSendVerificationCode(driverPhone);

    }



}
