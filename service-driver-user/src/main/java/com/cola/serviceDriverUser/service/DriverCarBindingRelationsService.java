package com.cola.serviceDriverUser.service;

import com.cola.internal.constant.CommonStatusEnum;
import com.cola.internal.constant.DriverCarConstant;
import com.cola.internal.dto.DriverCarBindingRelations;
import com.cola.internal.dto.ResponseResult;
import com.cola.serviceDriverUser.mapper.DriverCarBindingRelationsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverCarBindingRelationsService {


    @Autowired
    private DriverCarBindingRelationsDao driverCarBindingRelationsDao;



    public ResponseResult bind(DriverCarBindingRelations driverCarBindingRelations){

        driverCarBindingRelations.setBindingTime(LocalDateTime.now());
        driverCarBindingRelations.setBingState(DriverCarConstant.DRIVER_CAR_BIND);

        driverCarBindingRelationsDao.insert(driverCarBindingRelations);


        return ResponseResult.success("");



    }


    public ResponseResult unbind(DriverCarBindingRelations driverCarBindingRelations){

        // 先查询是否有绑定关系
        Map<String,Object> map = new HashMap<>();
        map.put("driver_id",driverCarBindingRelations.getDriverId());
        map.put("car_id",driverCarBindingRelations.getCarId());
        map.put("bing_state",DriverCarConstant.DRIVER_CAR_BIND);

        List<DriverCarBindingRelations> driverCarBindingRelationsList = driverCarBindingRelationsDao.selectByMap(map);
        if (driverCarBindingRelationsList.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getValue());
        }
        DriverCarBindingRelations carBindingRelations = driverCarBindingRelationsList.get(0);
        carBindingRelations.setBingState(DriverCarConstant.DRIVER_CAR_UNBIND);
        // 解绑时间
        carBindingRelations.setUnBindingTime(LocalDateTime.now());

        driverCarBindingRelationsDao.updateById(carBindingRelations);

        return ResponseResult.success("");

    }




}
