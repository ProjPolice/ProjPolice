package com.projpolice.domain.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.request.ProjectInsertRequest;
import com.projpolice.domain.project.request.ProjectModifyRequest;
import com.projpolice.domain.project.request.ProjectUserAddRequest;
import com.projpolice.domain.project.response.ProjectUserListResponse;
import com.projpolice.domain.project.service.ProjectService;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@Tag(name = "프로젝트 컨트롤러", description = "프로젝트를 담당하는 컨트롤러입니다.")
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Retrieves the details of a project.
     *
     * @param projectId The ID of the project to select.
     * @return ResponseEntity<BaseResponse < ProjectDetailData>> The response entity containing the project details.
     */
    @GetMapping("/{projectId}")
    @Operation(summary = "프로젝트 조회", security = @SecurityRequirement(name = "Authorization"), description = "사용자의 Access Token과 projectId를 받아 프로젝트를 조회합니다.")
    public ResponseEntity<BaseResponse<ProjectDetailData>> selectProject(@PathVariable("projectId") long projectId) {
        ProjectDetailData projectDetailData = projectService.selectProjectDetail(projectId);
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<ProjectDetailData>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트 조회 성공")
                    .data(projectDetailData)
                    .build()
            );
    }

    /**
     * Inserts a new project.
     *
     * @param request The request object containing the project details.
     * @return ResponseEntity<BaseResponse < ProjectDetailData>> The response entity containing the newly created project details.
     */
    @PostMapping
    @Operation(summary = "프로젝트 생성", security = @SecurityRequirement(name = "Authorization"), description = "사용자의 Access Token과 생성할 프로젝트의 정보를 받아 프로젝트를 생성합니다.")
    public ResponseEntity<BaseResponse<ProjectDetailData>> insertProject(@RequestBody ProjectInsertRequest request) {
        ProjectDetailData projectDetailData = projectService.insertProject(request);

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<ProjectDetailData>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트 생성 성공")
                    .data(projectDetailData)
                    .build()
            );
    }

    /**
     * Deletes a project.
     *
     * @param id The ID of the project to delete.
     * @return ResponseEntity<BaseResponse < BaseIdItem>> The response entity indicating the success of the project deletion.
     */
    @DeleteMapping("/{project_id}")
    @Operation(summary = "프로젝트 삭제", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 Project Id를 받아 해당 프로젝트를 삭제합니다.")
    public ResponseEntity<BaseResponse<BaseIdItem>> deleteProject(@PathVariable("project_id") long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<BaseIdItem>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트 삭제 성공")
                    .data(projectService.deleteProject(id))
                    .build()
            );
    }

    /**
     * Modifies an existing project.
     *
     * @param id      The unique identifier of the project to be modified.
     * @param request The request object containing the updated project details.
     * @return ResponseEntity<BaseResponse < ProjectDetailData>> The response entity containing the modified project details.
     */
    @PatchMapping("/{project_id}")
    @Operation(summary = "프로젝트 수정", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 project Id, 그리고 수정할 데이터를 받아 해당 프로젝트를 수정합니다.")
    public ResponseEntity<BaseResponse<ProjectDetailData>> modifyProject(@PathVariable("project_id") long id,
        @RequestBody
        ProjectModifyRequest request) {
        ProjectDetailData projectDetailData = projectService.modifyProject(id, request);

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                BaseResponse.<ProjectDetailData>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트 수정 성공")
                    .data(projectDetailData)
                    .build()
            );
    }

    /**
     * Lists the users of a project.
     *
     * @param id The ID of the project.
     * @return ResponseEntity<BaseResponse < ProjectUserListResponse>> The response entity containing the list of project users.
     */
    @GetMapping("/{project_id}/users")
    @Operation(summary = "프로젝트 멤버 조회", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 project id를 받아 해당 프로젝트에 속한 멤버들을 조회합니다.")
    public ResponseEntity<BaseResponse<ProjectUserListResponse>> listProjectUser(@PathVariable("project_id") long id) {
        List<UserIdNameImgItem> members = projectService.listProjectUser(id);

        return ResponseEntity.ok()
            .body(
                BaseResponse.<ProjectUserListResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트 멤버 조회 성공")
                    .data(new ProjectUserListResponse(members))
                    .build()
            );
    }

    /**
     * Adds a user to a project.
     *
     * @param id      The ID of the project.
     * @param request The request object containing the details of the user to be added.
     * @return ResponseEntity<BaseResponse < UserIdNameImgItem>> The response entity containing the added project user.
     */
    @PostMapping("/{project_id}/users")
    @Operation(summary = "프로젝트 멤버 등록", security = @SecurityRequirement(name = "Authorization"), description = "Access Toekn과 project Id와 추가할 사용자의 이메일을 받아 해당 사용자를 프로젝트에 멤버로 추가합니다.")
    public ResponseEntity<BaseResponse<UserIdNameImgItem>> addProjectUser(@PathVariable("project_id") long id,
        @RequestBody
        ProjectUserAddRequest request) {

        return ResponseEntity.ok()
            .body(
                BaseResponse.<UserIdNameImgItem>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트에 멤버 추가 성공")
                    .data(projectService.addProjectUser(id, request))
                    .build()
            );
    }

    /**
     * Deletes a user from a project.
     *
     * @param projectId The ID of the project.
     * @param userId    The ID of the user to be deleted.
     * @return ResponseEntity<BaseResponse < BaseIdItem>> The response entity indicating the success of the user deletion.
     */
    @PostMapping("/{project_id}/users/{user_id}")
    @Operation(summary = "프로젝트 멤버 삭제", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 user Id를 받아 프로젝트 멤버를 삭제합니다.")
    public ResponseEntity<BaseResponse<BaseIdItem>> deleteProjectUser(@PathVariable("project_id") long projectId,
        @PathVariable("user_id") long userId) {
        return ResponseEntity.ok()
            .body(
                BaseResponse.<BaseIdItem>builder()
                    .code(HttpStatus.OK.value())
                    .message("프로젝트 멤버 삭제 성공")
                    .data(projectService.deleteProjectUser(projectId, userId))
                    .build()
            );
    }
}
