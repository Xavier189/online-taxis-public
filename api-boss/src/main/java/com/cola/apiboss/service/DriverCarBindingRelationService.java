package com.cola.apiboss.service;


import com.cola.apiboss.remote.ServiceDriverUserClient;
import com.cola.internal.dto.DriverCarBindingRelations;
import com.cola.internal.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DriverCarBindingRelationService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult bind(DriverCarBindingRelations driverCarBindingRelations){
        return serviceDriverUserClient.bind(driverCarBindingRelations);

    }

    public ResponseResult unbind(DriverCarBindingRelations driverCarBindingRelations){

        return serviceDriverUserClient.unbind(driverCarBindingRelations);

    }



}
