package com.projpolice.domain.user.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.task.response.UserTaskRangeResponse;
import com.projpolice.domain.task.service.TaskService;
import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.domain.user.response.UserLogoutResponse;
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
    private final TaskService taskService;

    /**
     * 회원가입 처리
     *
     * @param request
     * @return 회원의 아이디
     */
    @PostMapping(value = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "회원가입", description = "새로운 사용자 정보를 입력 받아 회원가입을 진행합니다. \n 단, 스웨거에서는 이미지가 없으면 에러 발생")
    public ResponseEntity<? extends BaseResponse<BaseIdItem>> join(@ModelAttribute UserJoinRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<BaseIdItem>builder()
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
    public ResponseEntity<? extends BaseResponse<UserInfoResponse>> updateUserInfo(
        @ModelAttribute UserUpdateRequest request) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserInfoResponse>builder()
                .code(HttpStatus.OK.value())
                .message("사용자 정보 수정 성공")
                .data(userService.updateUserInfo(request))
                .build()
            );
    }

    /**
     * 로그인 처리, FCM 토큰 입력받기
     *
     * @param request
     * @return 인증에 필요한 Jwt Token 발급
     */
    @PostMapping
    @Operation(summary = "사용자 로그인", security = @SecurityRequirement(name = "Authorization"), description = "이메일과 패스워드를 받아 Access Token을 반환합니다. 추가적으로 FCM을 위해 토큰 입력을 해야합니다.")
    public ResponseEntity<BaseResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest request) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserLoginResponse>builder()
                .code(200)
                .message("로그인 성공")
                .data(userService.login(request))
                .build()
            );
    }

    @DeleteMapping
    @Operation(summary = "사용자 로그아웃", security = @SecurityRequirement(name = "Authorization"), description = "로그아웃 합니다. 추가로 사용자의 FCM 토큰도 삭제합니다.")
    public ResponseEntity<BaseResponse<UserLogoutResponse>> logout() {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserLogoutResponse>builder()
                .code(200)
                .message("로그아웃 성공")
                .data(userService.logout())
                .build()
            );
    }



    @GetMapping("/tasks")
    @Operation(summary = "현재 나의 세부 작업 리스트 조회", security = @SecurityRequirement(name = "Authorization"), description = "Access Token에 해당하는 사용자의 현재 세부 리스트를 조회 기간에 따라 반환합니다.")
    public ResponseEntity<BaseResponse<UserTaskRangeResponse>> selectUserTaskRelatedDataWithRange(
        @RequestParam(value = "startDate", required = false) LocalDate startDate,
        @RequestParam(value = "endDate", required = false) LocalDate endDate
    ) {
        if (startDate == null) {
            startDate = LocalDate.now().minusDays(3);
        }
        if (endDate == null) {
            endDate = LocalDate.now().plusDays(3);
        }
        return ResponseEntity.ok()
            .body(new BaseResponse<>(
                new UserTaskRangeResponse(
                    taskService.selectUserTaskRelatedDataWithRange(startDate, endDate)
                )
            ));
    }
}
