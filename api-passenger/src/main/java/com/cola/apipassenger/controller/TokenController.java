package com.cola.apipassenger.controller;


import com.cola.apipassenger.service.TokenService;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.responese.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {


    @Autowired
    private TokenService tokenService;




    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的 refreshtoken：" + refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }

}
