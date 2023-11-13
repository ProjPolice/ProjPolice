package com.projpolice.domain.task.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.Http;
import com.projpolice.domain.task.dto.ProjectTaskDetails;
import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.request.TaskUpdateRequest;
import com.projpolice.domain.task.response.TaskGetResponse;
import com.projpolice.domain.task.response.TaskUpdateResponse;
import com.projpolice.domain.task.service.TaskService;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.base.BaseResponse;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Tag(name = "세부작업 컨트롤러", description = "세부작업을 담당하는 컨트롤러입니다.")
public class TaskController {
    private final TaskService taskService;

    /**
     * 세부 작업 생성 요청 처리
     *
     * @param taskCreateRequest
     * @return 생성한 세부 작업의 상세 내용
     */
    @PostMapping
    @Operation(summary = "세부작업 생성", security = @SecurityRequirement(name = "Authorization"), description = "사용자의 Access Token과 생성할 세부 작업 데이터를 받아 생성합니다.")
    public ResponseEntity<BaseResponse<TaskDetailItem>> createTask(@RequestBody TaskCreateRequest taskCreateRequest) {
        return ResponseEntity.ok()
            .body(BaseResponse.<TaskDetailItem>builder()
                .code(HttpStatus.OK.value())
                .message("세부 작업 생성 성공")
                .data(taskService.createTask(taskCreateRequest))
                .build());
    }

    /**
     * 세부작업 수정 요청 처리
     *
     * @param taskId
     * @param taskUpdateRequest
     * @return 수정한 세부 작업의 값
     */
    @PatchMapping("/{task_id}")
    @Operation(summary = "세부작업 수정", security = @SecurityRequirement(name = "Authorization"), description = "세부 작업 수정합니다.")
    public ResponseEntity<BaseResponse<TaskUpdateResponse>> updateTask(@PathVariable("task_id") Long taskId,
        @RequestBody TaskUpdateRequest taskUpdateRequest) {
        return ResponseEntity.ok()
            .body(BaseResponse.<TaskUpdateResponse>builder()
                .code(HttpStatus.OK.value())
                .message("세부 작업 수정 성공")
                .data(taskService.updateTask(taskId, taskUpdateRequest))
                .build());
    }

    /**
     * 세부작업 조회 요청 처리
     *
     * @param taskId
     * @return 세부 작업의 값
     */
    @GetMapping("/{task_id}")
    @Operation(summary = "세부작업 조회", security = @SecurityRequirement(name = "Authorization"), description = "세부 작업 조회합니다.")
    public ResponseEntity<BaseResponse<TaskGetResponse>> getTask(@PathVariable("task_id") Long taskId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<TaskGetResponse>builder()
                .code(HttpStatus.OK.value())
                .message("세부 작업 조회 성공")
                .data(taskService.getTask(taskId))
                .build());
    }

    /**
     * 세부작업 삭제 요청 처리
     *
     * @param taskId
     * @return 삭제된 세부작업의 Id
     */
    @DeleteMapping("/{task_id}")
    @Operation(summary = "세부작업 삭제", security = @SecurityRequirement(name = "Authorization"), description = "세부 작업을 삭제합니다.")
    public ResponseEntity<BaseResponse<BaseIdItem>> deleteTask(@PathVariable("task_id") Long taskId) {
        return ResponseEntity.ok()
            .body(BaseResponse.<BaseIdItem>builder()
                .code(HttpStatus.OK.value())
                .message("세부 작업 삭제 성공")
                .data(taskService.deleteTask(taskId))
                .build());
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProjectTaskDetails>>> getTasksBy(
        @RequestParam(value = "project_id", required = false) Long projectId,
        @RequestParam(value = "epic_id", required = false) Long epicId) {

        if (projectId == null && epicId == null) {
            throw new BaseException(ExceptionInfo.INVALID_METADATA);
        }

        List<ProjectTaskDetails> list;
        String message;

        if (epicId == null) {
            list = taskService.selectProjectTaskDetailByProjectId(projectId);
            message = "프로젝트의 세부 작업 리스트 조회 성공";
        } else {
            list = taskService.selectEpicTaskDetailByEpicId(epicId);
            message = "할일의 세부 작업 리스트 조회 성공";
        }

        return ResponseEntity.ok()
            .body(BaseResponse.<List<ProjectTaskDetails>>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .data(list)
                .build()
            );
    }
}
