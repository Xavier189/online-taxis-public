package com.cola.apipassenger.service;


import com.cola.apipassenger.remote.VerificationCodeClient;
import com.cola.internal.common.SingleResponse;
import com.cola.internal.constant.IdentityConstant;
import com.cola.internal.constant.TokenConstant;
import com.cola.internal.exception.BizException;
import com.cola.internal.exception.ErrorStatus;
import com.cola.internal.exception.SysException;
import com.cola.internal.request.VerificationCodeAddCmd;
import com.cola.internal.responese.NumberCodeResponse;
import com.cola.internal.responese.TokenResponse;
import com.cola.internal.utils.RedisPrefixUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class VerificationCodeService {

    private final VerificationCodeClient verificationCodeClient;
    //    private final ServicePassengerUserClient servicePassengerUserClient;
    private final StringRedisTemplate stringRedisTemplate;


    /**
     * 生成验证码 *
     *
     * @param passengerPhone 手机号
     * @return
     */
    public void generatorCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        int verificationCode = verificationCodeClient.getNumberCode(6)
                .map(SingleResponse::getData)
                .map(NumberCodeResponse::getNumberCode)
                .orElseThrow(() -> new SysException(ErrorStatus.VERIFICATION_CODE_CLIENT_ERROR));
        log.info("得到的验证码:{}", verificationCode);
        // 存入Redis  设置两分钟过期
        stringRedisTemplate.opsForValue().set(RedisPrefixUtils.buildVerificationKey(passengerPhone),
                String.valueOf(verificationCode), 2, TimeUnit.MINUTES);
        // todo 通过短信服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通
    }

    /**
     * 校验验证码 *
     *
     * @param passengerPhone   手机号
     * @param verificationCode 验证码
     * @return
     */
    public SingleResponse<TokenResponse> checkCode(String passengerPhone, String verificationCode) {
        // 根据手机号去redis去读验证码
        // 根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(RedisPrefixUtils.buildVerificationKey(passengerPhone));

        // 校验验证码
        if (StringUtils.isBlank(codeRedis) || !verificationCode.trim().equals(codeRedis))
            throw new BizException(ErrorStatus.VERIFICATION_CODE_ERROR);

        // 判断原来是否有用户，并进行处理
        VerificationCodeAddCmd loginRequest = new VerificationCodeAddCmd();
        loginRequest.setPassengerPhone(passengerPhone);
        //servicePassengerUserClient.loginUser(loginRequest);


        //颁发令牌
        //        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        //        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        String accessToken = "test1234";
        String refreshToken = "test5678";
        log.info("生成的accessToken，refreshToken是:{}，{}", accessToken, refreshToken);

        // 将token存入redis
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);
        return SingleResponse.of(TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build());
    }
}