package com.cola.apidriver.service;


import com.cola.apidriver.remote.ServiceDriverUserClient;
import com.cola.apidriver.remote.ServiceVerificationCodeClient;
import com.cola.internal.constant.CommonStatusEnum;
import com.cola.internal.constant.DriverCarConstant;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.responese.DriverUserExistsResponse;
import com.cola.internal.responese.NumberCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeService {


    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;



    public ResponseResult checkAndSendVerificationCode(String driverPhone){

        // 查询service-driver-user，改手机号的司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);

        String driverPhoneDb = driverUserExistsResponseResponseResult.getData().getDriverPhone();
        Integer ifExists = driverUserExistsResponseResponseResult.getData().getIfExists();
        if (ifExists == DriverCarConstant.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITST.getCode(),CommonStatusEnum.DRIVER_NOT_EXITST.getValue());

        }
        log.info(driverPhone + "的司机存在");

        // 调用service-verificationcode生成验证码并存入redis中
        ResponseResult<NumberCodeResponse> verificationCode = serviceVerificationCodeClient.getVerificationCode(6);
        NumberCodeResponse numberCodeResponse = verificationCode.getData();
        int number = numberCodeResponse.getNumberCode();
        log.info("此处验证码是：" + number);

        // 调用第三方接口发送验证码
        return ResponseResult.success();

    }


}
