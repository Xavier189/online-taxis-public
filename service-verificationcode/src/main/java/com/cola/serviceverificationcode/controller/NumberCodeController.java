package com.cola.serviceverificationcode.controller;

import com.cola.internal.common.SingleResponse;
import com.cola.internal.responese.NumberCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class NumberCodeController {

    // 定义验证码字符集（排除易混淆字符）
    private static final String DIGITS = "0123456789";
    private static final String LETTERS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";

    private static final String ALPHANUMERIC = DIGITS + LETTERS;

    private static final SecureRandom RANDOM = new SecureRandom();

    @GetMapping("/numberCode/{size}")
    public ResponseEntity<SingleResponse<NumberCodeResponse>> numberCode(@PathVariable("size") int size) {
        // 生成验证码
        int captchaCode = Integer.parseInt(generateNumericCaptcha(size));
        NumberCodeResponse response = new NumberCodeResponse(captchaCode);
        return ResponseEntity.ok(SingleResponse.of(response));
    }

    /**
     * 生成数字验证码（纯数字）
     *
     * @param length 验证码长度
     */
    public static String generateNumericCaptcha(int length) {
        return generateCaptcha(DIGITS, length);
    }

    /**
     * 生成字母数字混合验证码（排除易混淆字符）
     *
     * @param length 验证码长度
     */
    public static String generateAlphanumericCaptcha(int length) {
        return generateCaptcha(ALPHANUMERIC, length);
    }

    private static String generateCaptcha(String charPool, int length) {
        if (length <= 0) throw new IllegalArgumentException("长度必须为正整数");

        return IntStream.range(0, length)
                .map(i -> RANDOM.nextInt(charPool.length()))
                .mapToObj(index -> String.valueOf(charPool.charAt(index)))
                .collect(Collectors.joining());
    }

}
