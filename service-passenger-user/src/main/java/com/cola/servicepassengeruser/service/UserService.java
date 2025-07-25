package com.cola.servicepassengeruser.service;


import com.cola.internalcommon.constant.CommonStatusEnum;
import com.cola.internalcommon.dto.ResponseResult;
import com.cola.internalcommon.dto.PassengerUser;
import com.cola.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrReg(String passengerPhone){

        // 根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        System.out.println(passengerUsers.size()==0?"无记录":passengerUsers.get(0).getPassengerName());

        // 判断用户信息是否存在
        // 如果不存在，执行插入
        if (passengerUsers.size() == 0){
            // 如果不存在，插入用户信息
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);


        }



        System.out.println("user service");
        return ResponseResult.success();

    }


    /**
     * 根据手机号查询用户信息 *
     * @param passengerPhone
     * @return
     */
    public ResponseResult getUserByPhone(String passengerPhone){
        // 根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        if (passengerUsers == null){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),CommonStatusEnum.USER_NOT_EXISTS.getValue());

        } else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }

    }


}
