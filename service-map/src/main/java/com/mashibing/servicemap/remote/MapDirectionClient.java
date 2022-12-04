package com.mashibing.servicemap.remote;


import com.mashibing.internalcommon.constant.AmapConfigConstant;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.responese.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;



    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        // 组装调用URL
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstant.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=" + depLongitude + "," + depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination=" + destLongitude + "," + destLatitude);
        urlBuilder.append("&");
        // base是基本信息，用all是全部信息
        urlBuilder.append("extensions=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key=" + amapKey);

        log.info(urlBuilder.toString());


        // 调用高德接口
        // https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=json
        // &key=58dfbde5b0ec087c93d5caa71ee8d08a
        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = forEntity.getBody();
        log.info("高德地图路径规划 返回信息：" + directionString);

        // 解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionString);


        return directionResponse;

    }


    private DirectionResponse parseDirectionEntity(String directionString){

        DirectionResponse directionResponse = null;

        try {

            // 最外层
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AmapConfigConstant.STATUS)){
                int status = result.getInt(AmapConfigConstant.STATUS);
                if (status == 1){
                    if (result.has(AmapConfigConstant.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstant.ROUTE);
                        JSONArray pathArray = routeObject.getJSONArray(AmapConfigConstant.PATHS);
                        JSONObject pathObject = pathArray.getJSONObject(0);

                        directionResponse = new DirectionResponse();

                        if (pathObject.has(AmapConfigConstant.DISTANCE)){
                            int distance = pathObject.getInt(AmapConfigConstant.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.has(AmapConfigConstant.DURATION)){
                            int duration = pathObject.getInt(AmapConfigConstant.DURATION);
                            directionResponse.setDuration(duration);
                        }


                    }
                }
            }


        }catch (Exception e){

        }



        return directionResponse;



    }


}
