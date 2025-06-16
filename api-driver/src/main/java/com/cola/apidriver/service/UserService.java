package com.cola.apidriver.service;


import com.cola.apidriver.remote.ServiceDriverUserClient;
import com.cola.common.dto.DriverUser;
import com.cola.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult updateUser(DriverUser driverUser){

        return serviceDriverUserClient.updateDriverUser(driverUser);


    }

}
