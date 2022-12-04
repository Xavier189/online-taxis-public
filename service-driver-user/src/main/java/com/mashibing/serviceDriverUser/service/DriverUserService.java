package com.mashibing.serviceDriverUser.service;


import com.mashibing.internalcommon.constant.CommonStatusEnum;
import com.mashibing.internalcommon.constant.DriverCarConstant;
import com.mashibing.internalcommon.dto.DriverUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.serviceDriverUser.mapper.DriverUserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
