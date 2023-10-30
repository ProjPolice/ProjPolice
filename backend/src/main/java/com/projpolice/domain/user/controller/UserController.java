package com.projpolice.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.global.common.base.BaseResponse;
import com.projpolice.global.common.base.ResponseCode;

import lombok.RequiredArgsConstructor;

/**
 * This class handles user related API endpoints
 * and performs user login functionality.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest request){
        // TODO: add Business logic
        return ResponseEntity.ok()
            .body(BaseResponse.<UserLoginResponse>builder()
                .code(ResponseCode.OK)
                .message("User login success")
                .body(
                    UserLoginResponse.builder()
                        .accessToken("accessToken")
                        .refreshToken("refreshToken")
                        .build()
                )
                .build()
            );
    }
}
