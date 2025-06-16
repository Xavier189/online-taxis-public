package com.cola.serviceprice.controller;


import com.cola.common.dto.ResponseResult;
import com.cola.common.request.ForecastPriceDTO;
import com.cola.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForecastPriceController {


    @Autowired
    private ForecastPriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();

        return forecastPriceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude);

    }

}
