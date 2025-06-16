package com.cola.apiboss.controller;


import com.cola.apiboss.service.CarService;
import com.cola.apiboss.service.DriverUserService;
import com.cola.common.dto.Car;
import com.cola.common.dto.DriverUser;
import com.cola.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {


    @Autowired
    private DriverUserService driverUserService;
    @Autowired
    private CarService carService;


    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

        return driverUserService.addDriverUser(driverUser);

    }

    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){

        return driverUserService.updateDriverUser(driverUser);

    }


    /**
     * 添加车辆信息 *
     * @param car
     * @return
     */
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return carService.addCar(car);


    }









}
