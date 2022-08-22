package com.mashibing.apipassenger.service;


import com.mashibing.apipassenger.remote.ServiceVefificationcodeClient;
import com.mashibing.internalcommon.constant.CommonStatusEnum;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.responese.NumberCodeResponse;
import com.mashibing.internalcommon.responese.TokenResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {


    @Autowired
    private ServiceVefificationcodeClient serviceVefificationcodeClient;

    // 乘客验证码前缀
    private String verficationCodePrefix = "passenger-verfication-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 生成验证码 *
     * @param passengerPhone 手机号
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code：" + numberCode);


        // 存入Redis
        System.out.println("将验证码存入Redis");
        // key,value,过期时间
        String key = generatorKeyByPhone(passengerPhone);
        // 存入Redis  设置两分钟过期
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        // 处理返回{
        //    code:    number;
        //    message: string;
        //}
//        JSONObject result = new JSONObject();
//        result.put("code",1);
//        result.put("message","success");


        // 通过短信服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通



        return ResponseResult.success("");

    }


    /**
     * 根据手机号生成key *
     * @param passengerPhone 手机号
     * @return
     */
    private String generatorKeyByPhone(String passengerPhone){

        return verficationCodePrefix + passengerPhone;

    }


    /**
     * 校验验证码 *
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone,String verificationCode){

        // 根据手机号去redis去读验证码
        // 获取key
        String key = generatorKeyByPhone(passengerPhone);
        // 根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("Redis中的value" + codeRedis);


        // 校验验证码
        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }



        // 判断原来是否有用户，并进行处理

        //颁发令牌


        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);

    }

}


