package com.cola.apidriver.remote;


import com.cola.internal.dto.DriverUser;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.responese.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);


    @RequestMapping(method = RequestMethod.GET,value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone);


}
