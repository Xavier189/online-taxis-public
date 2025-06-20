package com.cola.internal.request;

import com.cola.internal.common.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationCodeAddCmd extends Command {


    /**
     * 乘客手机号 *
     */
    @NotBlank(message = "手机号不能为空", groups = {GroupA.class, GroupB.class})
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "不符合手机号码规则", groups = {GroupA.class, GroupB.class})
    private String passengerPhone;

    /**
     * 验证码 *
     */
    @NotBlank(message = "验证码不能为空", groups = GroupB.class)
    @Pattern(regexp = "\\d{6}", message = "验证码必须是6位数字", groups = GroupB.class)
    private String verificationCode;

    /**
     * 司机手机号
     */
    private String driverPhone;
}
