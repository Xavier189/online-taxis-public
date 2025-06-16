package com.cola.apiboss.service;


import com.cola.apiboss.remote.ServiceDriverUserClient;
import com.cola.common.dto.Car;
import com.cola.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult addCar(Car car){

        return serviceDriverUserClient.addCar(car);

    }




}
