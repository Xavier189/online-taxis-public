package com.mashibing.servicemap.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.servicemap.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {


    @Autowired
    private DistrictService districtService;



    @GetMapping("/dic-district")
    public ResponseResult initDicDistrict(String keyWords){

        return districtService.initDicDistrict(keyWords);

    }


}
