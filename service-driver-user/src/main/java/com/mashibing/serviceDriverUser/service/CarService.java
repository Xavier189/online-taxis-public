package com.mashibing.serviceDriverUser.service;


import com.mashibing.internalcommon.dto.Car;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.serviceDriverUser.mapper.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
