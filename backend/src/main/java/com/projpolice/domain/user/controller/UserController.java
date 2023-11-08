package com.projpolice.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.domain.user.service.UserService;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * This class handles user related API endpoints
 * and performs user login functionality.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "사용자 컨트롤러", description = "사용자를 담당하는 컨트롤러입니다.")
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 처리
     *
     * @param request
     * @return 회원의 아이디
     */
    @PostMapping(value = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "회원가입", description = "새로운 사용자 정보를 입력 받아 회원가입을 진행합니다.")
    public ResponseEntity<? extends BaseResponse<BaseIdItem>> join(UserJoinRequest request,
        @RequestPart(required = false) MultipartFile image) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<BaseIdItem>builder()
                    .code(HttpStatus.OK.value())
                    .message("회원가입 성공")
                    .data(userService.join(request, image))
                    .build()
            );
    }

    /**
     * 사용자 정보 조회
     *
     * @return 인증된 사용자의 정보
     */
    @Operation(summary = "회원 정보 조회", security = @SecurityRequirement(name = "Authorization"), description = "Access Token을 받아 사용자의 정보를 반환합니다.")
    @GetMapping
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
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "사용자 정보 수정", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 수정할 정보를 받아 사용자의 정보를 수정합니다.")
    public ResponseEntity<? extends BaseResponse<UserInfoResponse>> updateUserInfo(UserUpdateRequest request,
        @RequestPart(required = false) MultipartFile image) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserInfoResponse>builder()
                .code(HttpStatus.OK.value())
                .message("사용자 정보 수정 성공")
                .data(userService.updateUserInfo(request, image))
                .build()
            );
    }

    /**
     * 로그인 처리
     *
     * @param request
     * @return 인증에 필요한 Jwt Token 발급
     */
    @PostMapping
    @Operation(summary = "사용자 로그인", security = @SecurityRequirement(name = "Authorization"), description = "이메일과 패스워드를 받아 Access Token을 반환합니다.")
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
