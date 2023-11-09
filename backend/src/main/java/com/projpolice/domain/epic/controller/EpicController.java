package com.projpolice.domain.epic.controller;

import java.time.LocalDate;
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

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;
import com.projpolice.domain.epic.request.EpicCreateRequest;
import com.projpolice.domain.epic.request.EpicUpdateRequest;
import com.projpolice.domain.epic.service.EpicService;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/epics")
@Tag(name = "할일 컨트롤러", description = "할일을 담당하는 컨트롤러입니다.")
public class EpicController {
    private final EpicService epicService;

    /**
     * 할일 생성하는 요청 처리
     *
     * @param epicCreateRequest
     * @return 저장한 할일의 상세 내용
     */
    @PostMapping
    @Operation(summary = "할일 추가", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 등록할 할일 정보를 받아 할일을 등록합니다.")
    public ResponseEntity<BaseResponse<EpicDetailData>> createEpic(@RequestBody EpicCreateRequest epicCreateRequest) {

        return ResponseEntity.ok()
            .body(BaseResponse.<EpicDetailData>builder()
                .code(HttpStatus.OK.value())
                .message("할일 생성 성공")
                .data(epicService.createEpic(epicCreateRequest))
                .build());
    }

    /**
     * 해당 할일 상세 조회 요청 처리
     *
     * @param epiceId
     * @return 조회한 할일의 상세 내용
     */
    @GetMapping("/{epicId}")
    @Operation(summary = "할일 조회", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 epic id를 받아 할일을 조회합니다.")
    public ResponseEntity<BaseResponse<EpicDetailData>> getEpicDetail(@PathVariable("epiceId") long epiceId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<EpicDetailData>builder()
                .code(HttpStatus.OK.value())
                .message("할일 상세 조회 성공")
                .data(epicService.getEpic(epiceId))
                .build());
    }

    /**
     * 해당 할일을 수정하는 요청 처리
     *
     * @param epiceId
     * @param epicUpdateRequest
     * @return 수정된 할일 상세 정보 조회
     */
    @PatchMapping("/{epicId}")
    @Operation(summary = "할일 수정", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 수정할 할일 정보를 받아 할일을 수정합니다.")
    public ResponseEntity<BaseResponse<EpicDetailData>> updateEpic(@PathVariable("epiceId") long epiceId
        , @RequestBody EpicUpdateRequest epicUpdateRequest) {

        return ResponseEntity.ok()
            .body(BaseResponse.<EpicDetailData>builder()
                .code(HttpStatus.OK.value())
                .message("할일 수정 성공")
                .data(epicService.updateEpic(epiceId, epicUpdateRequest))
                .build());
    }

    /**
     * 해당 할일을 삭제하는 요청 처리
     * @param epiceId
     * @return 삭제한 할일의 id
     */
    @DeleteMapping("/{epicId}")
    @Operation(summary = "할일 삭제", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 삭제할 epic id를 받아 할일을 삭제합니다.")
    public ResponseEntity<BaseResponse<BaseIdItem>> deleteEpic(@PathVariable("epiceId") long epiceId) {

        return ResponseEntity.ok()
            .body(BaseResponse.<BaseIdItem>builder()
                .code(HttpStatus.OK.value())
                .message("할일 삭제 성공")
                .data(epicService.deleteEpic(epiceId))
                .build());
    }

    @GetMapping
    @Operation(summary = "프로젝트의 할일 리스트 조회", security = @SecurityRequirement(name = "Authorization"), description = "Access Token과 Project Id, 조회 기간을 받아 프로젝트의 할일 리스트를 반환합니다.")
    public ResponseEntity<BaseResponse<List<EpicProjectedItem>>> selectProjectEpicsWithDateRange(
        @RequestParam("project_id") long projectId, @RequestParam(value = "start", required = false)
    LocalDate start_date, @RequestParam(value = "end_date", required = false) LocalDate end_date) {
        return ResponseEntity.ok()
            .body(BaseResponse.<List<EpicProjectedItem>>builder()
                .code(HttpStatus.OK.value())
                .message("프로젝트의 할일 리스트 조회 성공")
                .data(epicService.selectProjectEpicsWithDateRange(projectId, start_date, end_date))
                .build()
            );
    }
}
