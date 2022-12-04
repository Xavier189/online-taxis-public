package com.mashibing.apiboss.service;


import com.mashibing.apiboss.remote.ServiceDriverUserClient;
import com.mashibing.internalcommon.dto.DriverCarBindingRelations;
import com.mashibing.internalcommon.dto.ResponseResult;
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
