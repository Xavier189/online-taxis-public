package com.cola.apipassenger.service;


import com.cola.apipassenger.remote.ServicePassengerUserClient;
import com.cola.internal.dto.PassengerUser;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.dto.TokenResult;
import com.cola.internal.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


//    @Autowired
//    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult gtUserByAccessToken(String accessToken){

        log.info("accessToken是："+ accessToken);
        // 解析accessToken
        //TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        TokenResult tokenResult = null;
        String phone = tokenResult.getPhone();

        log.info("手机号：" + phone);
        // 根据手机号查询用户信息
//        VerificationCodeDto verificationCodeDto = new VerificationCodeDto();
//        verificationCodeDto.setPassengerPhone(phone);
        ResponseResult<PassengerUser> userByPhone = null;

        // 返回用户信息
//        PassengerUser passengerUser = new PassengerUser();

        return ResponseResult.success(userByPhone.getData());
    }


}
