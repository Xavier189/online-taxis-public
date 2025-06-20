package com.cola.apipassenger.controller;

import com.cola.apipassenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;


//    @GetMapping("/users")
//    public ResponseResult getUser(HttpServletRequest request){
//
//        // 解析请求头header中的token，反解析token拿到用户的手机号
//
//
//        String accessToken = request.getHeader("Authorization");
//
//
//        return userService.gtUserByAccessToken(accessToken);
//
//    }


}
