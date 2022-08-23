package com.mashibing.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mashibing.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // 盐
    private static final String SIGN = "CPFmsb!@#";

    // 如果该常量属性需要多方法使用，建议抽出为常量声明
    private static final String JWT_KEY_PHONE = "passengerPhone";

    // 假设暂定1是乘客 2是司机
    private static final String JWT_KEY_IDENTITY = "identity";

    // token类型
    private static final String JWT_TOKEN_TYPE = "tokenType";


    // 生成token
    public static String generatorToken(String passengerPhone,String identity,String tokenType){

        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);


        // token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();


        JWTCreator.Builder builder = JWT.create();

        // 整合map
        map.forEach(
                (k,v) -> {
                builder.withClaim(k,v);
        });

        // 整合过期时间
//        builder.withExpiresAt(date);

        // 生成Token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));


        return sign;
    }


    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());



        Map<String,String> map = new HashMap<>();

        String token = generatorToken("18113830901","1","access");

        System.out.println("生成的token是:"+token);

        TokenResult tokenResult = parseToken(token);

        System.out.println("解析token后的手机号：" + tokenResult.getPhone() + ",身份标识:" + tokenResult.getIdentity());
    }


    // 解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

        // 反解析出来的手机号
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        // 反解析出来的身份标识
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentity(identity);
        tokenResult.setPhone(phone);

        return tokenResult;

    }


}
