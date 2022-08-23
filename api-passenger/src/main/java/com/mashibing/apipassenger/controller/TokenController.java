package com.mashibing.apipassenger.controller;


import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.responese.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        return null;
    }

}
