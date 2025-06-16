package com.cola.apiboss.service;

import com.cola.apiboss.remote.ServiceDriverUserClient;
import com.cola.internal.dto.DriverUser;
import com.cola.internal.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverUserService {



    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult addDriverUser(DriverUser driverUser){




        return serviceDriverUserClient.addDriverUser(driverUser);
    }



    public ResponseResult updateDriverUser(DriverUser driverUser){

        return serviceDriverUserClient.updateDriverUser(driverUser);

    }






}
