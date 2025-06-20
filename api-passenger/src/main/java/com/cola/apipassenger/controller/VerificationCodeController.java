package com.cola.apipassenger.controller;

import com.cola.apipassenger.service.VerificationCodeService;
import com.cola.internal.common.Response;
import com.cola.internal.common.SingleResponse;
import com.cola.internal.request.GroupA;
import com.cola.internal.request.GroupB;
import com.cola.internal.request.VerificationCodeAddCmd;
import com.cola.internal.responese.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class VerificationCodeController {
    private final VerificationCodeService verificationCodeService;


    @GetMapping("/verification-code")
    public ResponseEntity<Response> verificationCode(@Validated(GroupA.class) @RequestBody VerificationCodeAddCmd verificationRequest) {
        verificationCodeService.generatorCode(verificationRequest.getPassengerPhone());
        return ResponseEntity.ok(Response.buildSuccess());
    }


    @PostMapping("/verification-code-check")
    public ResponseEntity<SingleResponse<TokenResponse>> checkVerificationCode(@Validated(GroupB.class) @RequestBody VerificationCodeAddCmd verificationRequest) {
        SingleResponse<TokenResponse> tokenResponse = verificationCodeService.checkCode(verificationRequest.getPassengerPhone(), verificationRequest.getVerificationCode());
        return ResponseEntity.ok(tokenResponse);
    }
}
