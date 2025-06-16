package com.cola.servicemap.controller;


import com.cola.common.dto.ResponseResult;
import com.cola.common.request.ForecastPriceDTO;
import com.cola.servicemap.service.DriectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direction")
public class DriectionController {


    @Autowired
    private DriectionService driectionService;


    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();

//        return forecastPriceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude);


        return driectionService.driving(depLongitude,depLatitude,destLongitude,destLatitude);


    }


}
