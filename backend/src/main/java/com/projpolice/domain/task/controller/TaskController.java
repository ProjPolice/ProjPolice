package com.projpolice.domain.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.service.TaskService;
import com.projpolice.global.common.base.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    /**
     * 세부 작업 생성 요청 처리
     * @param taskCreateRequest
     * @return 생성한 세부 작업의 상세 내용
     */
    @PostMapping()
    public ResponseEntity<BaseResponse<TaskDetailItem>> createTask(@RequestBody TaskCreateRequest taskCreateRequest) {

        return ResponseEntity.ok()
            .body(BaseResponse.<TaskDetailItem>builder()
                .code(HttpStatus.OK.value())
                .message("세부 작업 생성 성공")
                .data(taskService.createTask(taskCreateRequest))
                .build());
    }
}
