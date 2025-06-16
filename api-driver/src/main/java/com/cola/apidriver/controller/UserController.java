package com.cola.apidriver.controller;


import com.cola.apidriver.service.UserService;
import com.cola.common.dto.DriverUser;
import com.cola.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){

        return userService.updateUser(driverUser);


    }

}
