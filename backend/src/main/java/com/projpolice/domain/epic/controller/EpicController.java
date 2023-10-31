package com.projpolice.domain.epic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.request.EpicCreateRequest;
import com.projpolice.domain.epic.request.EpicUpdateRequest;
import com.projpolice.domain.epic.service.EpicService;
import com.projpolice.global.common.base.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/epics")
public class EpicController {
    private final EpicService epicService;

    /**
     * 할일 생성하는 요청 처리
     *
     * @param epicCreateRequest
     * @return 저장한 할일의 상세 내용
     */
    @PostMapping()
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
    @GetMapping("/{epiceId}")
    public ResponseEntity<BaseResponse<EpicDetailData>> getEpicDetail(@PathVariable("epiceId") Long epiceId) {

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
    @PatchMapping("/{epiceId}")
    public ResponseEntity<BaseResponse<EpicDetailData>> updateEpic(@PathVariable("epiceId") Long epiceId
        , @RequestBody EpicUpdateRequest epicUpdateRequest) {

        return ResponseEntity.ok()
            .body(BaseResponse.<EpicDetailData>builder()
                .code(HttpStatus.OK.value())
                .message("할일 수정 성공")
                .data(epicService.updateEpic(epiceId, epicUpdateRequest))
                .build());
    }
}
