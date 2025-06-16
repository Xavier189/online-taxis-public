package com.cola.servicemap.service;


import com.cola.internal.constant.AmapConfigConstant;
import com.cola.internal.constant.CommonStatusEnum;
import com.cola.internal.dto.DicDistrict;
import com.cola.internal.dto.ResponseResult;
import com.cola.servicemap.mapper.DicDistrictMapper;
import com.cola.servicemap.remote.MapDicDistictClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistrictService {


    @Autowired
    private MapDicDistictClient mapDicDistictClient;
    @Autowired
    private DicDistrictMapper dicDistrictMapper;


    public ResponseResult initDicDistrict(String keyWords){


        // https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        // 拼装请求URL
        String dicDistrictResult = mapDicDistictClient.initDicDistrict(keyWords);

        // 解析结果
        JSONObject dicDistrictJSONObject= JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJSONObject.getInt(AmapConfigConstant.STATUS);
        if (status != 1 ){
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(),CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray countryJsonArray = dicDistrictJSONObject.getJSONArray(AmapConfigConstant.DISTRICTS);
        for (int country = 0;country<countryJsonArray.size();country++){
            JSONObject countryJsonArrayJSONObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonArrayJSONObject.getString(AmapConfigConstant.ADCODE);
            String countryName = countryJsonArrayJSONObject.getString(AmapConfigConstant.NAME);
            String countryParentAddressCode = "0";
            String countrylevel = countryJsonArrayJSONObject.getString(AmapConfigConstant.LEVEL);

           insertDicDistrict(countryName,countryParentAddressCode,countryAddressCode,countrylevel);

           JSONArray proviceJsonArray = countryJsonArrayJSONObject.getJSONArray(AmapConfigConstant.DISTRICTS);
           for (int p= 0;p < proviceJsonArray.size();p++){
               JSONObject proviceJsonObject = proviceJsonArray.getJSONObject(p);
               String proviceAddressCode = proviceJsonObject.getString(AmapConfigConstant.ADCODE);
               String proviceName = proviceJsonObject.getString(AmapConfigConstant.NAME);
               String proviceParentAddressCode = countryAddressCode;
               String proviceLevel = proviceJsonObject.getString(AmapConfigConstant.LEVEL);

               insertDicDistrict(proviceName,proviceParentAddressCode,proviceAddressCode,proviceLevel);

               JSONArray cityJsonArry = proviceJsonObject.getJSONArray(AmapConfigConstant.DISTRICTS);
               for (int c = 0;c<cityJsonArry.size();c++){
                   JSONObject cityJsonObject = cityJsonArry.getJSONObject(c);
                   String cityAddressCode = cityJsonObject.getString(AmapConfigConstant.ADCODE);
                   String cityName = cityJsonObject.getString(AmapConfigConstant.NAME);
                   String cityParentAddressCode = proviceAddressCode;
                   String cityLevel = cityJsonObject.getString(AmapConfigConstant.LEVEL);

                   insertDicDistrict(cityName,cityParentAddressCode,cityAddressCode,cityLevel);

                   JSONArray districtJsonArry = cityJsonObject.getJSONArray(AmapConfigConstant.DISTRICTS);
                   for (int d = 0;d<districtJsonArry.size();d++){
                       JSONObject districtJsonObject = districtJsonArry.getJSONObject(d);
                       String districtAddressCode = districtJsonObject.getString(AmapConfigConstant.ADCODE);
                       String districtName = districtJsonObject.getString(AmapConfigConstant.NAME);
                       String districtParentAddressCode = cityAddressCode;
                       String districtLevel = districtJsonObject.getString(AmapConfigConstant.LEVEL);

                       if (districtLevel.equals(AmapConfigConstant.STREET)){
                           continue;
                       }
                       insertDicDistrict(districtName,districtParentAddressCode,districtAddressCode,districtLevel);


                   }
               }
           }

        }




        return ResponseResult.success("");

    }



    public void insertDicDistrict(String name,String parentAddressCode,String addressCode,String level){
        // 数据库对象
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressName(name);
        dicDistrict.setAddressCode(addressCode);
        int levelInt = generateLevel(level);
        dicDistrict.setLevel(levelInt);
        dicDistrict.setParentAddressCode(parentAddressCode);

        // 插入数据库
        dicDistrictMapper.insert(dicDistrict);
    }



    public int generateLevel(String level){
        int levelInt = 0;
        if (level.trim().equals("country")){
            levelInt = 0;
        } else if (level.trim().equals("province")){
            levelInt = 1;
        } else if (level.trim().equals("city")){
            levelInt = 2;
        } else if (level.trim().equals("district")){
            levelInt = 3;
        }

        return levelInt;
    }


}
