package com.projpolice.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
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

    /**
     * 회원가입 처리
     *
     * @param request
     * @return 회원의 아이디
     */
    @PostMapping("/join")
    public ResponseEntity<? extends BaseResponse<UserJoinResponse>> join(@RequestBody UserJoinRequest request){

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<UserJoinResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message("회원가입 성공")
                    .data(userService.join(request))
                    .build()
            );
    }

    /**
     * 사용자 정보 조회
     *
     * @return 인증된 사용자의 정보
     */
    @GetMapping()
    public ResponseEntity<? extends BaseResponse<UserInfoResponse>> getUserInfo() {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserInfoResponse>builder()
                .code(HttpStatus.OK.value())
                .message("사용자 정보 조회 성공")
                .data(userService.getUserInfo())
                .build()
            );
    }

    /**
     * 사용자 정보 수정
     *
     * @param request
     * @return 수정된 사용자의 정보
     */
    @PatchMapping()
    public ResponseEntity<? extends BaseResponse<UserInfoResponse>> updateUserInfo(@RequestBody UserUpdateRequest request) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserInfoResponse>builder()
                .code(HttpStatus.OK.value())
                .message("사용자 정보 수정 성공")
                .data(userService.updateUserInfo(request))
                .build()
            );
    }

    /**
     * 로그인 처리
     *
     * @param request
     * @return 인증에 필요한 Jwt Token 발급
     */
    @PostMapping()
    public ResponseEntity<? extends BaseResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest request) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserLoginResponse>builder()
                .code(200)
                .message("로그인 성공")
                .data(userService.login(request))
                .build()
            );
    }
}
