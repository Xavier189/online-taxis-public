package com.cola.apipassenger.service;


import com.cola.apipassenger.remote.ServicePriceClient;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.request.ForecastPriceDTO;
import com.cola.internal.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

//    @Autowired
//    private ServicePriceClient servicePriceClient;

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
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        ResponseResult<ForecastPriceResponse> responseResult = null;
        double price = responseResult.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();

        forecastPriceResponse.setPrice(price);

        return ResponseResult.success(forecastPriceResponse);




    }




}
