package com.cola.common.responese;


import lombok.Data;

@Data
public class DriverUserExistsResponse {


    private String driverPhone;

    private int ifExists;

}
