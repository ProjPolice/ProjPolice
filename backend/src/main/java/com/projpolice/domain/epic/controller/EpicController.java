package com.projpolice.domain.epic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.request.EpicCreateRequest;
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
     * @return ResponseEntity<BaseResponse < EpicDetailData>>
     */
    @PostMapping()
    public ResponseEntity<BaseResponse<EpicDetailData>> createEpic(@RequestBody EpicCreateRequest epicCreateRequest) {

        return ResponseEntity.ok()
            .body(BaseResponse.<EpicDetailData>builder()
                .code(HttpStatus.OK.value())
                .message("할일 생성 완료")
                .data(epicService.createEpic(epicCreateRequest))
                .build());
    }
}
