package com.cola.apiboss.remote;


import com.cola.internal.dto.Car;
import com.cola.internal.dto.DriverCarBindingRelations;
import com.cola.internal.dto.DriverUser;
import com.cola.internal.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);


    @RequestMapping(method = RequestMethod.POST,value = "/car")
    public ResponseResult addCar(@RequestBody Car car);

    /**
     * 调用服务service-driver-user的绑定车辆服务 *
     * @param driverCarBindingRelations
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelations driverCarBindingRelations);

    /**
     * 调用服务service-driver-user的解绑车辆服务
     * @param driverCarBindingRelations
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelations driverCarBindingRelations);

}
