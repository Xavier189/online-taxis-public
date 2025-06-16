package com.cola.servicepassengeruser.controller;


import com.cola.internalcommon.dto.ResponseResult;
import com.cola.internalcommon.request.VerificationCodeDTO;
import com.cola.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDto){

        String passengerPhone = verificationCodeDto.getPassengerPhone();

        userService.loginOrReg(passengerPhone);
        System.out.println("手机号是：" + passengerPhone);
        return ResponseResult.success();

    }

    /**
     * *
     * @param passengerPhone
     * @return
     */
    @GetMapping("/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable("phone") String passengerPhone){



        userService.getUserByPhone(passengerPhone);

        return userService.getUserByPhone(passengerPhone);

    }

}
