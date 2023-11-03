package com.projpolice.domain.epic.service;

import java.time.LocalDate;
import java.util.List;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;
import com.projpolice.domain.epic.request.EpicCreateRequest;
import com.projpolice.domain.epic.request.EpicUpdateRequest;
import com.projpolice.global.common.base.BaseIdItem;

public interface EpicService {
    EpicDetailData createEpic(EpicCreateRequest epicCreateRequest);

    EpicDetailData getEpic(Long id);

    EpicDetailData updateEpic(Long id, EpicUpdateRequest epicUpdateRequest);

    BaseIdItem deleteEpic(Long id);

    List<EpicProjectedItem> selectProjectEpicsWithDateRange(long project_id, LocalDate startDate, LocalDate endDate);
}
