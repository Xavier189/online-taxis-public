package com.mashibing.serviceDriverUser.controller;


import com.mashibing.internalcommon.dto.Car;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.serviceDriverUser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.CardTerminals;

@RestController
public class CarController {


    @Autowired
    private CarService carService;


    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){


        return carService.addCar(car);

    }




}
