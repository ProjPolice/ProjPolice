package com.projpolice.domain.project.controller;

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
import com.projpolice.domain.project.service.ProjectService;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.base.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Retrieves the details of a project.
     *
     * @param projectId The ID of the project to select.
     * @return ResponseEntity<BaseResponse < ProjectDetailData>> The response entity containing the project details.
     */
    @GetMapping("/{projectId}")
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

    @DeleteMapping("/{project_id}")
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

    @PatchMapping("/{project_id}")
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
}
