package com.mashibing.serviceDriverUser.controller;

import com.mashibing.internalcommon.dto.DriverUser;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DriverUserController {


    @Autowired
    private DriverUserService driverUserService;


    /**
     * 添加司机信息*
     * @param driverUser
     * @return
     */
    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

//        driverUserService.addDriverUser(driverUser);
        log.info(JSONObject.fromObject(driverUser).toString());

        return driverUserService.addDriverUser(driverUser);

    }


    /**
     * 修改司机信息*
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);

    }



}
