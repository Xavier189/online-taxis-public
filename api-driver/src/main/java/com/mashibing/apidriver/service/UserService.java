package com.mashibing.apidriver.service;


import com.mashibing.apidriver.remote.ServiceDriverUserClient;
import com.mashibing.internalcommon.dto.DriverUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {


    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult updateUser(DriverUser driverUser){

        return serviceDriverUserClient.updateDriverUser(driverUser);


    }

}
