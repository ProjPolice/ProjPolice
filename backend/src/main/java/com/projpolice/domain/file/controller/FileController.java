package com.projpolice.domain.file.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.dto.FileResourceItem;
import com.projpolice.domain.file.request.FileUploadRequest;
import com.projpolice.domain.file.service.FileService;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.base.BaseResponse;
import com.projpolice.global.common.error.exception.BadRequestException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "세부작업의 파일들 조회", security = @SecurityRequirement(name = "Authroization"), description = "세부작업의 파일들을 조회하는 메소드입니다.")
    public ResponseEntity<BaseResponse<List<FileDetailItem>>> getTaskFile(
        @RequestParam(name = "project_id", required = false) Long projectId,
        @RequestParam(name = "task_id", required = false) Long taskId) {
        if ((projectId == null && taskId == null) || (projectId != null && taskId != null)) {
            throw new BadRequestException(ExceptionInfo.INVALID_METADATA);
        }

        List<FileDetailItem> list;
        if (projectId == null) {
            list = fileService.getTaskFileByTaskId(taskId);
        } else {
            list = fileService.getTaskFileByProjectId(projectId);
        }

        return ResponseEntity.ok()
            .body(BaseResponse.<List<FileDetailItem>>builder()
                .code(HttpStatus.OK.value())
                .message("세부작업의 파일들 조회")
                .data(list)
                .build());
    }

    /**
     * 세부작업에 파일을 생성하는 메소드
     *
     * @param request
     * @return FIleDetailItem
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "파일 업로드", security = @SecurityRequirement(name = "Authroization"), description = "파일을 업로드 하는 메서드입니다.")
    public ResponseEntity<BaseResponse<FileDetailItem>> uploadFile(FileUploadRequest request,
        @RequestParam(name = "task_id") long taskId) {

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
    @DeleteMapping("/{file_id}")
    @Operation(summary = "파일 삭제", description = "파일들을 삭제하는 메소드입니다.")
    public ResponseEntity<BaseResponse<BaseIdItem>> deleteFile(@RequestParam(name = "file_id") long fileId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<BaseIdItem>builder()
                .code(HttpStatus.OK.value())
                .message("파일 삭제 성공")
                .data(fileService.deleteFile(fileId))
                .build());
    }

    /**
     * 파일을 다운로드 할 수 있는 메소드
     * @param fileId
     * @return
     */
    @GetMapping("/{file_id}")
    @Operation(summary = "파일 다운로드", description = "파일을 다운로드하는 메소드입니다.")
    public ResponseEntity<Resource> downloadFileByFileId(@PathVariable(name = "file_id") Long fileId) {
        FileResourceItem fileResourceItem = fileService.getFileOfFile(fileId);

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment() // (6)
                .filename(fileResourceItem.getName(), StandardCharsets.UTF_8)
                .build()
                .toString())
            .body(fileResourceItem.getResource());
    }
}
