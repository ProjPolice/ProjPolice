package com.projpolice.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.project.service.ProjectService;
import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.response.UserJoinResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.domain.user.response.UserProjectPageResponse;
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
    
    private final ProjectService projectService;

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

    @PostMapping
    public ResponseEntity<? extends BaseResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest request) {

        return ResponseEntity.ok()
            .body(BaseResponse.<UserLoginResponse>builder()
                .code(200)
                .message("User login success")
                .data(userService.login(request))
                .build()
            );
    }


    /**
     * Retrieves a paginated list of user projects.
     *
     * @param page The page number to retrieve. Default value is 1.
     * @param numOfRows The number of rows per page. Default value is 10.
     * @return The ResponseEntity containing the paginated list of user projects.
     */
    @GetMapping("/projects")
    public ResponseEntity<BaseResponse<UserProjectPageResponse>> pageUserProject(
        @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int numOfRows) {

        // TODO: logged user 추가
        long userId = 1;

        return ResponseEntity.ok()
            .body(
                BaseResponse.<UserProjectPageResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message("내 프로젝트 리스트 조회 성공")
                    .data(
                        new UserProjectPageResponse(projectService.selectProjectOfUser(userId, page, numOfRows))
                    )
                    .build()
            );
    }
}
