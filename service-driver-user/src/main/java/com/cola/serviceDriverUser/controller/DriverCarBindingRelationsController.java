package com.cola.serviceDriverUser.controller;


import com.cola.internal.dto.DriverCarBindingRelations;
import com.cola.internal.dto.ResponseResult;
import com.cola.serviceDriverUser.service.DriverCarBindingRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarBindingRelationsController {

    @Autowired
    private DriverCarBindingRelationsService driverCarBindingRelationsService;


    /**
     * 绑定车辆*
     * @param driverCarBindingRelations
     * @return
     */
    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelations driverCarBindingRelations){

        return driverCarBindingRelationsService.bind(driverCarBindingRelations);



    }

    /**
     * 解绑车辆*
     * @param driverCarBindingRelations
     * @return
     */
    @PostMapping("/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelations driverCarBindingRelations){

        return driverCarBindingRelationsService.unbind(driverCarBindingRelations);



    }




}
