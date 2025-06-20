package com.cola.internal.utils;

public class RedisPrefixUtils {


    // 乘客验证码前缀
    public static String PASSENGER_VERIFICATION_PREFIX = "passenger-verification-code-";
    // token前缀
    public static String tokenPrefix = "token-";


    /**
     * 根据手机号生成验证码 key
     *
     * @param passengerPhone 手机号
     * @return
     */
    public static String buildVerificationKey(String passengerPhone) {
        return PASSENGER_VERIFICATION_PREFIX + passengerPhone;

    }


    /**
     * 根据手机号和身份标识生成token的key信息
     *
     * @param passengerPhone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String passengerPhone, String identity, String tokenType) {
        return tokenPrefix + passengerPhone + "-" + identity + "-" + tokenType;

    }


}
