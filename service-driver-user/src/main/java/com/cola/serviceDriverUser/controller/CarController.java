package com.cola.serviceDriverUser.controller;


import com.cola.internal.dto.Car;
import com.cola.internal.dto.ResponseResult;
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
