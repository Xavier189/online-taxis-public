package com.cola.servicemap.service;

import com.cola.common.dto.ResponseResult;
import com.cola.common.responese.DirectionResponse;
import com.cola.servicemap.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriectionService {


    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * 根据起点终点经纬度获取距离（米）和时长（分钟）*
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult driving(String depLongitude,String depLatitude,String destLongitude,String destLatitude){

        // 调用第三方的地图接口
        // https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=json
        // &key=58dfbde5b0ec087c93d5caa71ee8d08a
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);


//        DirectionResponse directionResponse = new DirectionResponse();
//        directionResponse.setDistance(123);
//        directionResponse.setDuration(123);


        return ResponseResult.success(direction);

    }


}
