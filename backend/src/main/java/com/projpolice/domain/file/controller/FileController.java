package com.projpolice.domain.file.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.file.dto.FileBaseItem;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.request.FileUploadRequest;
import com.projpolice.domain.file.service.FileService;
import com.projpolice.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
@Tag(name = "파일 컨트롤러", description = "파일을 담당하는 컨트롤러입니다.")
public class FileController {
    private final FileService fileService;

    /**
     * 세부작업의 파일들을 조회하는 메소드
     *
     * @return List<FIleDetailItem>
     */
    @GetMapping
    public ResponseEntity<BaseResponse<List<FileDetailItem>>> getTaskFile(@RequestParam(name = "task_id") long taskId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<List<FileDetailItem>>builder()
                .code(HttpStatus.OK.value())
                .message("세부작업의 파일들 조회")
                .data(fileService.getTaskFile(taskId))
                .build());

    }

    /**
     * 세부작업에 파일을 생성하는 메소드
     *
     * @param request
     * @return FIleDetailItem
     */
    @PostMapping
    public ResponseEntity<BaseResponse<FileDetailItem>> uploadFile(@RequestBody FileUploadRequest request, @RequestParam(name = "task_id") long taskId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<FileDetailItem>builder()
                .code(HttpStatus.OK.value())
                .message("파일 생성 성공")
                .data(fileService.uploadFile(request, taskId))
                .build());

    }

    /**
     * 파일을 삭제하는 메소드
     *
     * @return FIleBaseItem
     */
    @DeleteMapping
    public ResponseEntity<BaseResponse<FileBaseItem>> deleteFile(@RequestParam(name = "file_id") long fileId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<FileBaseItem>builder()
                .code(HttpStatus.OK.value())
                .message("파일 삭제 성공")
                .data(fileService.deleteFile(fileId))
                .build());
    }
}
