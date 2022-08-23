package com.mashibing.apipassenger.service;


import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {


    /**
     * 根据出发地和目的地的经纬度 计算出预估价格 *
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){

        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + depLongitude);
        log.info("目的地纬度：" + destLatitude);

        log.info("调用计价服务");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();

        forecastPriceResponse.setPrice(12.3);

        return ResponseResult.success(forecastPriceResponse);




    }




}