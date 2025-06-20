package com.cola.internal.responese;


import com.cola.internal.common.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenResponse extends Command {
    private String accessToken;
    private String refreshToken;

}
