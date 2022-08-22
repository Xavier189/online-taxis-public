package com.mashibing.servicepassengeruser.service;


import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.VerificationCodeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {


    public ResponseResult loginOrReg(String PassengerPhone){

        // 根据手机号查询用户信息

        // 判断用户信息是否存在

        // 如果不存在，执行插入

        System.out.println("user service");
        return ResponseResult.success();

    }


}
