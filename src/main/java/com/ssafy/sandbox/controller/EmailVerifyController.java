package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.EmailVerifyReq;
import com.ssafy.sandbox.controller.request.SendEmailReq;
import com.ssafy.sandbox.controller.response.SuccessSendRes;
import com.ssafy.sandbox.controller.response.SuccessVerifyRes;
import com.ssafy.sandbox.domain.emailVerify.service.EmailVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailVerifyController {
    private final EmailVerifyService emailVerifyService;

    @PostMapping("")
    public ResponseEntity<SuccessSendRes> sendEmailVerifyCode(@RequestBody SendEmailReq request) {
        emailVerifyService.sendEmailVerifyCode(request);

        return ResponseEntity.ok(new SuccessSendRes(true));
    }

    @PostMapping("/authentication")
    public ResponseEntity<SuccessVerifyRes> verifyEmailCode(@RequestBody EmailVerifyReq request) {
        emailVerifyService.verifyEmailCode(request);

        return ResponseEntity.ok(new SuccessVerifyRes(true));
    }
}
