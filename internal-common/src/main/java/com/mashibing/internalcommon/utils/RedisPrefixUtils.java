package com.mashibing.internalcommon.utils;

public class RedisPrefixUtils {



    // 乘客验证码前缀
    public static String verficationCodePrefix = "passenger-verfication-code-";
    // token前缀
    public static String tokenPrefix = "token-";


    /**
     * 根据手机号生成key *
     * @param passengerPhone 手机号
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone){

        return verficationCodePrefix + passengerPhone;

    }


    /**
     * 根据手机号和身份标识生成token的key信息 *
     * @param passengerPhone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String passengerPhone,String identity,String tokenType){
        return tokenPrefix + passengerPhone + "-" + identity + "-" + tokenType;

    }



}
