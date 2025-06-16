package com.cola.serviceprice.service;


import com.cola.internal.constant.CommonStatusEnum;
import com.cola.internal.dto.PriceRule;
import com.cola.internal.dto.ResponseResult;
import com.cola.internal.request.ForecastPriceDTO;
import com.cola.internal.responese.DirectionResponse;
import com.cola.internal.responese.ForecastPriceResponse;
import com.cola.internal.utils.BigDecimalUtils;
import com.cola.serviceprice.mapper.PriceRuleMapper;
import com.cola.serviceprice.remote.ServiceMapClient;
import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceService {


    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){

        log.info("server-出发地经度：" + depLongitude);
        log.info("server-出发地纬度：" + depLatitude);
        log.info("server-目的地经度：" + depLongitude);
        log.info("server-目的地纬度：" + destLatitude);

        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);

        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离：" + distance + "，时长：" + duration);

        log.info("读取计价规则");
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if (priceRules.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());

        }

        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离、时长和计价规则，计算价格");
        double price = getPrice(distance, duration, priceRule);


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();

        forecastPriceResponse.setPrice(price);

        return ResponseResult.success(forecastPriceResponse);

    }

    /**
     *  根据距离、时长和计价规则，计算最终价格 *
     * @param distance
     * @param duration
     * @param priceRule
     * @return
     */
    private double getPrice(Integer distance,Integer duration,PriceRule priceRule ){
//        BigDecimal price = new BigDecimal(0);
        double price = 0;
        // 起步价
        Double startFare = priceRule.getStartFare();

        price = BigDecimalUtils.add(startFare,price);
//        BigDecimal startFarePrice = new BigDecimal(startFare);
//        price = price.add(startFarePrice);

        // 里程费
        // 总里程 m
        // 总里程 km
        double distanceMile = BigDecimalUtils.divide(distance,1000);
//        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP);
        // 起步里程
        double startMile = (double) priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtils.substaact(distanceMile,startMile);
//        BigDecimal startMileDecimal = new BigDecimal(startMile);
//        double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        // 最终收费里程
        double mile = distanceSubtract<0?0:distanceSubtract;// 如果小于0就取0
//        BigDecimal mileDecimal = new BigDecimal(mile);
        // 计程单价
        double unitPricePerMile = priceRule.getUnitPricePerMile();
//        BigDecimal unitPriceDecimal = new BigDecimal(unitPricePerMile);
        // 里程价格
        double mileFare = BigDecimalUtils.multiply(mile, unitPricePerMile);
//        double mileFare = mileDecimal.multiply(unitPriceDecimal).setScale(2,BigDecimal.ROUND_HALF_UP);
        price = BigDecimalUtils.add(price,mileFare);

        // 时长费
        // 总时长 s
//        BigDecimal time = new BigDecimal(duration);
        // 总时长 m
        double timeMinute = BigDecimalUtils.divide(duration,60);
        // 时长计时单价
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
//        BigDecimal unitPriceMinuteDecimal = new BigDecimal(unitPricePerMinute);
        // 时长价格
        double timeFare = BigDecimalUtils.multiply(timeMinute,unitPricePerMinute);
//        BigDecimal timeFare = timeDecimal.multiply(unitPriceMinuteDecimal).setScale(2,BigDecimal.ROUND_HALF_UP);
        price = BigDecimalUtils.add(price,timeFare);

        // 取两位小数
        BigDecimal priceDecimal = BigDecimal.valueOf(price);
        priceDecimal = priceDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

        return priceDecimal.doubleValue();

    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(3);
//        System.out.println(getPrice(6500,1800,priceRule));
//    }


}
