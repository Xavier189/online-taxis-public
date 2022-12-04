package com.mashibing.servicemap.remote;


import com.mashibing.internalcommon.constant.AmapConfigConstant;
import com.mashibing.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDicDistictClient {


    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    public String initDicDistrict(String keyWords){


        // https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        // 拼装请求URL
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstant.DISTRICT_URL);
        urlBuilder.append("?");
        urlBuilder.append("keywords=" + keyWords);
        urlBuilder.append("&");
        urlBuilder.append("subdistrict=3");
        urlBuilder.append("&");
        urlBuilder.append("key=" + amapKey);

        log.info(urlBuilder.toString());


        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = forEntity.getBody();
//        log.info("高德地图行政区划 返回信息：" + directionString);


        return directionString;

    }



}
