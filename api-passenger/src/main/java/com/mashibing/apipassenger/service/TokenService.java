package com.mashibing.apipassenger.service;

import com.mashibing.internalcommon.constant.CommonStatusEnum;
import com.mashibing.internalcommon.constant.TokenConstant;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.dto.TokenResult;
import com.mashibing.internalcommon.responese.TokenResponse;
import com.mashibing.internalcommon.utils.JwtUtils;
import com.mashibing.internalcommon.utils.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    public ResponseResult refreshToken(String refreshTokenSrc){

        // 解析refreshtoken
        TokenResult refreshTokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (refreshTokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = refreshTokenResult.getPhone();
        String identity = refreshTokenResult.getIdentity();
        // 去读取redis中的refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstant.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        // 校验token
        if ((StringUtils.isBlank(refreshTokenRedis)) || (!refreshTokenRedis.trim().equals(refreshTokenSrc.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        // 生成双token
        String refreshToken = JwtUtils.generatorToken(phone,identity,TokenConstant.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone,identity,TokenConstant.ACCESS_TOKEN_TYPE);

        // 将两个token存入到redis
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity,TokenConstant.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }

}
