package com.cola.common.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class DriverUser {
    private Long id;

    /**
    * 注册地行政区划代码
    */
    private String adress;

    private String driverName;

    private String driverPhone;

    /**
    * 性别：1-男 2-女
    */
    private Integer driverGender;

    private LocalDate driverBirthday;

    /**
    * 民族
    */
    private String driverNation;

    private String driverContactAddress;

    private String licenseId;

    /**
    * 初次领取驾驶证日期
    */
    private LocalDate getDriverLicenseDate;

    /**
    * 驾驶证有效期起始
    */
    private LocalDate driverLicenseOn;

    /**
    * 驾驶证有效期结束
    */
    private LocalDate driverLicenseOff;

    /**
    * 是否巡游出租汽车：1-是 0-否
    */
    private Integer taxiDriver;

    /**
    * 网络预约出租汽车驾驶员资格证号
    */
    private String certificateNo;

    /**
    * 网络预约出租汽车驾驶员证发证机构
    */
    private String networkCarIssueOrganization;

    /**
    * 资格证发证日期
    */
    private LocalDate networkCarIssueDate;

    /**
    * 初次领取资格证日期
    */
    private LocalDate getNetworkCarIssueDate;

    /**
    * 资格证有效起始日期
    */
    private LocalDate networkCarProofOn;

    /**
    * 资格证有效截止日期
    */
    private LocalDate networkCarProofOff;

    /**
    * 报备日期
    */
    private LocalDate registerDate;

    /**
    * 服务类型：1-网络预约出租汽车 2-巡游出租汽车 3-私人小客车合乘
    */
    private Integer commercialType;

    /**
    * 驾驶员合同公司
    */
    private String contractCompany;

    /**
    * 合同时间有效起始期
    */
    private LocalDate contractOn;

    /**
    * 合同时间有效截止日期
    */
    private LocalDate contractOff;

    /**
    * 状态：0-有效 1-失效
    */
    private Integer state;



    /**
    * 创建时间
    */
    private LocalDateTime gmtCreate;

    /**
    * 修改时间
    */
    private LocalDateTime gmtModified;




}