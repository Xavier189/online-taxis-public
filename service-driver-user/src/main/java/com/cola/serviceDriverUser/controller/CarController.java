package com.cola.serviceDriverUser.controller;


import com.cola.common.dto.Car;
import com.cola.common.dto.ResponseResult;
import com.cola.serviceDriverUser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {


    @Autowired
    private CarService carService;


    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){


        return carService.addCar(car);

    }




}
