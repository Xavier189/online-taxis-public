package com.cola.common.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class DriverCarBindingRelations {
    private Long id;

    private Long driverId;

    private Long carId;

    /**
    * 绑定，1：绑定 2：解绑
    */
    private Integer bingState;

    /**
    * 绑定时间
    */
    private LocalDateTime bindingTime;

    /**
    * 解绑时间
    */
    private LocalDateTime unBindingTime;
}