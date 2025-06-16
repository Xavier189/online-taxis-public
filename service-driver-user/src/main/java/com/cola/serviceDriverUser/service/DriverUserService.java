package com.cola.serviceDriverUser.service;


import com.cola.internal.constant.CommonStatusEnum;
import com.cola.internal.constant.DriverCarConstant;
import com.cola.internal.dto.DriverUser;
import com.cola.internal.dto.ResponseResult;
import com.cola.serviceDriverUser.mapper.DriverUserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DriverUserService {

    @Autowired
    private DriverUserDao driverUserDao;


    public ResponseResult addDriverUser(DriverUser driverUser){

        LocalDateTime now = LocalDateTime.now();

        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);


        driverUserDao.insert(driverUser);

        return ResponseResult.success("");
    }


    public ResponseResult updateDriverUser(DriverUser driverUser){

        LocalDateTime now = LocalDateTime.now();

        driverUser.setGmtModified(now);


        driverUserDao.updateById(driverUser);

        return ResponseResult.success("");
    }



    public ResponseResult getDriverUserByPhone(String driverPhone){

        Map<String, Object> map = new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstant.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserDao.selectByMap(map);
        if (driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITST.getCode(),CommonStatusEnum.DRIVER_NOT_EXITST.getValue());
        }
        DriverUser driverUser = driverUsers.get(0);

        return ResponseResult.success(driverUser);

    }


//    public static void main(String[] args) {
//        // 无时间 2022-08-24
//        System.out.println(LocalDate.now());
//        // 有时间 2022-08-24T17:57:32.900
//        System.out.println(LocalDateTime.now());
//    }

}
