package com.mashibing.internalcommon.request;

import lombok.Data;

@Data
public class ForecastPriceDTO {


    /**
     * 出发点经度*
     */
    private String depLongitude;

    /**
     * 出发点纬度*
     */
    private String depLatitude;

    /**
     * 目的地经度 *
     */
    private String destLongitude;

    /**
     * 目的地纬度*
     */
    private String destLatitude;
}
