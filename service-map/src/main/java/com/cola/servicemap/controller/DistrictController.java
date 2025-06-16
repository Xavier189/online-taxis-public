package com.cola.servicemap.controller;

import com.cola.internal.dto.ResponseResult;
import com.cola.servicemap.service.DistrictService;
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
