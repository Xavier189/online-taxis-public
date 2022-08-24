package com.mashibing.serviceDriverUser.service;


import com.mashibing.internalcommon.dto.DriverUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.serviceDriverUser.mapper.DriverUserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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


//    public static void main(String[] args) {
//        // 无时间 2022-08-24
//        System.out.println(LocalDate.now());
//        // 有时间 2022-08-24T17:57:32.900
//        System.out.println(LocalDateTime.now());
//    }

}
