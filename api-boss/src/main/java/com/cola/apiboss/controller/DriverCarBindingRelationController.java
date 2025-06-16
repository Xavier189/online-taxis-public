package com.cola.apiboss.controller;


import com.cola.apiboss.service.DriverCarBindingRelationService;
import com.cola.internal.dto.DriverCarBindingRelations;
import com.cola.internal.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver-car-binding-relationship")
@Slf4j
public class DriverCarBindingRelationController {


    @Autowired
    private DriverCarBindingRelationService driverCarBindingRelationService;


    /**
     * 添加车辆与司机的绑定信息 *
     * @param driverCarBindingRelations
     * @return
     */
    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelations driverCarBindingRelations){
        return driverCarBindingRelationService.bind(driverCarBindingRelations);

    }


    /**
     * 解除车辆与司机的绑定信息 *
     * @param driverCarBindingRelations
     * @return
     */
    @PostMapping("/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelations driverCarBindingRelations){
        return driverCarBindingRelationService.unbind(driverCarBindingRelations);

    }




}
