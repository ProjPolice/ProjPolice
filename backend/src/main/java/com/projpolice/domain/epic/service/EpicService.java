package com.projpolice.domain.epic.service;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.request.EpicCreateRequest;

public interface EpicService {
    EpicDetailData createEpic(EpicCreateRequest epicCreateRequest);
}
