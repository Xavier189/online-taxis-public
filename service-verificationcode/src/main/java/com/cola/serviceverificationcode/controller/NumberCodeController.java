package com.cola.serviceverificationcode.controller;

import com.cola.common.dto.ResponseResult;
import com.cola.common.responese.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {



    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){

        System.out.println("size:" + size);

        // 生成验证码
        double matchRandom = (Math.random()*9 + 1) * (Math.pow(10,size-1));
        // 已生成5位整数含有小数数据
        System.out.println(matchRandom);
        // 截取小数点前的位数作为验证码
        int resultInt = (int)matchRandom;
        System.out.println("generator src code:" + resultInt);


        // {
        //    "code": 17,
        //    "message": "cupidatat quis",
        //    "data": {
        //        "numberCode": 20
        //    }
        //}
//        JSONObject result = new JSONObject();
//        result.put("code",1);
//        result.put("message","success");
//        JSONObject data = new JSONObject();
//        data.put("numberCode",resultInt);
//        result.put("data",data);

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return ResponseResult.success(response);

    }

}
