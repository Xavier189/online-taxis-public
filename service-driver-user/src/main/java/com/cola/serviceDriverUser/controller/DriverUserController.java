package com.cola.serviceDriverUser.controller;

import com.cola.internal.constant.DriverCarConstant;
import com.cola.internal.dto.DriverUser;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.responese.DriverUserExistsResponse;
import com.cola.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 查询司机
     * 如果需要按照司机的多个条件做查询，那么此处用/user地址名 * *
     * @param driverPhone
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone")String driverPhone){

//        String driverPhone = driverUser.getDriverPhone();
        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserByPhone.getData();
        // {
        //    "code": 53,
        //    "message": "veniam eiusmod esse",
        //    "data": {
        //        "driverPhone": "18122563536",
        //        "ifExists": 1 --司机存在
        //    }
        //}
        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();
        int ifExists = DriverCarConstant.DRIVER_EXISTS;
        if (driverUserDb == null){
            ifExists = DriverCarConstant.DRIVER_NOT_EXISTS;
            driverUserExistsResponse.setDriverPhone(driverPhone);
            driverUserExistsResponse.setIfExists(ifExists);
        } else {
            driverUserExistsResponse.setDriverPhone(driverPhone);
            driverUserExistsResponse.setIfExists(ifExists);
        }

        return ResponseResult.success(driverUserExistsResponse);


    }



}
