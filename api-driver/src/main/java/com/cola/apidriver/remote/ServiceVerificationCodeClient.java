package com.cola.apidriver.remote;


import com.cola.common.dto.ResponseResult;
import com.cola.common.responese.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {


    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> getVerificationCode(@PathVariable("size") int size);

}
