package com.cola.serviceDriverUser.service;


import com.cola.common.dto.Car;
import com.cola.common.dto.ResponseResult;
import com.cola.serviceDriverUser.mapper.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {


    @Autowired
    private CarDao carDao;


    public ResponseResult addCar(Car car){

        car.setGmtCreate(LocalDateTime.now());
        car.setGmtModified(LocalDateTime.now());

        carDao.insert(car);

        return ResponseResult.success("");

    }



}
