package com.mashibing.apipassenger.controller;


import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";

    }


    /**
     * 需要有token *
     * @return
     */

    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");

    }



    /**
     * 不需要token也能访问*
     * @return
     */
    @GetMapping("/noauthTest")
    public ResponseResult noauthTest(){
        return ResponseResult.success("noauth test");

    }

}
