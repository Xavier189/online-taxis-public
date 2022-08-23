package com.mashibing.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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

    //
    public static final String JWT_TOKEN_TIME = "tokenTime";


    // 生成token
    public static String generatorToken(String passengerPhone,String identity,String tokenType){

        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);



        // token过期时间
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();
        // 加入当前时间戳 保证每次生成的token不一样
        map.put(JWT_TOKEN_TIME,date.toString());


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


    /**
     * 校验token，主要判断token是否异常*
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){

//        boolean result = true;
        String resultString = "";
        TokenResult tokenResult = null;

        try {
            tokenResult = JwtUtils.parseToken(token);

        } catch (SignatureVerificationException e){
            // 签名错误异常
            resultString = "token sign error";
//            result = false;
        } catch (TokenExpiredException e){
            // token过期异常
            resultString = "token time out";
//            result = false;
        } catch (AlgorithmMismatchException e){
            // 算法异常
            resultString = "token AlgorithmMismatchException";
//            result = false;
        }
        catch (Exception e) {
            resultString = "token invalid";
//            result = false;
        }

        return tokenResult;

    }


}
