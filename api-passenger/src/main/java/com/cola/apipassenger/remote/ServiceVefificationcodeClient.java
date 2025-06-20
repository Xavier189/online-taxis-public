package com.cola.apipassenger.remote;


import com.cola.internal.common.SingleResponse;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.responese.NumberCodeResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

//@FeignClient("service-verificationcode")
public interface ServiceVefificationcodeClient {


//    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
//    Optional<SingleResponse<NumberCodeResponse>> getNumberCode(@PathVariable("size") int size);


}
