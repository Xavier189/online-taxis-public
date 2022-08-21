package com.mashibing.apipassenger.service;


import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {


    public String generatorCode(String passengerPhone){
        // 调用也正吗服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "111111";

        // 存入Redis
        System.out.println("将验证码存入Redis");

        // 处理返回{
        //    code:    number;
        //    message: string;
        //}
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");

        return result.toString();



    }


}
