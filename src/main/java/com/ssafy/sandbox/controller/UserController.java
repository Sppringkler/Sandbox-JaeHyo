package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.AuthenticationReq;
import com.ssafy.sandbox.controller.request.EmailAuthReq;
import com.ssafy.sandbox.controller.response.BooleanRes;
import com.ssafy.sandbox.controller.response.BooleanSuccessRes;
import com.ssafy.sandbox.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/email")
    public ResponseEntity<BooleanRes> sendEmailAuthCode(
        @RequestBody EmailAuthReq req) {

        userService.sendEmailAuthCode(req.getEmail());

        return ResponseEntity.ok(
                new BooleanRes(true)
        );
    }

    @PostMapping("/email/authentication")
    public ResponseEntity<BooleanSuccessRes> authenticate(
            @RequestBody AuthenticationReq req) {

        userService.authenticate(req.getEmail(), req.getAuthentication());

        return ResponseEntity.ok(
                new BooleanSuccessRes(true)
        );
    }


}
