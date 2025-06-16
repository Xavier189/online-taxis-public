package com.cola.apipassenger.remote;


import com.cola.internal.dto.PassengerUser;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {


    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult loginUser(@RequestBody VerificationCodeDTO verificationCodeDto);

    @RequestMapping(method = RequestMethod.GET,value = "/user/{phone}")
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable("phone") String passengerPhone);

}
