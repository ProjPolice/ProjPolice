package com.projpolice.domain.epic.service;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.request.EpicCreateRequest;
import com.projpolice.domain.epic.request.EpicUpdateRequest;

public interface EpicService {
    EpicDetailData createEpic(EpicCreateRequest epicCreateRequest);

    EpicDetailData getEpic(Long id);

    EpicDetailData updateEpic(Long id, EpicUpdateRequest epicUpdateRequest);
}
