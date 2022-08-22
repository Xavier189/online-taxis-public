package com.mashibing.servicepassengeruser.controller;


import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.VerificationCodeDto;
import com.mashibing.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDto verificationCodeDto){

        String passengerPhone = verificationCodeDto.getPassengerPhone();

        userService.loginOrReg(passengerPhone);
        System.out.println("手机号是：" + passengerPhone);
        return ResponseResult.success();

    }

}
