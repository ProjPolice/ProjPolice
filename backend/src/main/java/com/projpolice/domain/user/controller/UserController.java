package com.projpolice.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.response.UserJoinResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.domain.user.service.UserService;
import com.projpolice.global.common.base.BaseResponse;

import lombok.RequiredArgsConstructor;

/**
 * This class handles user related API endpoints
 * and performs user login functionality.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<? extends BaseResponse<UserJoinResponse>> join(@RequestBody UserJoinRequest request){

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<UserJoinResponse>builder()
                .code(HttpStatus.OK.value())
                .message("User login success")
                .data(userService.join(request))
                .build()
            );
    }

    @PostMapping("")
    public ResponseEntity<? extends BaseResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest request) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserLoginResponse>builder()
                .code(200)
                .message("User login success")
                .data(userService.login(request))
                .build()
            );
    }
}
