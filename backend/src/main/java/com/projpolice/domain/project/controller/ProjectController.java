package com.projpolice.domain.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.service.ProjectService;
import com.projpolice.global.common.base.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    ProjectService projectService;

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
}
