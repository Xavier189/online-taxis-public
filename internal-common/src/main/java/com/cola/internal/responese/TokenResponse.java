package com.cola.internal.responese;


import lombok.Data;

@Data
public class TokenResponse {


    private String accessToken;
    private String refreshToken;

}
