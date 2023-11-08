package com.projpolice.domain.epic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;

public interface EpicRedisInterface {

    Optional<EpicDetailData> findEpicDetailById(long epicId);

    void saveEpicDetail(EpicDetailData data);

    Optional<List<EpicProjectedItem>> selectProjectEpicsWithDateRange(long projectId, LocalDate startDate,
        LocalDate endDate);

    void saveProjectEpicsWithDateRange(long projectId, LocalDate startDate, LocalDate endDate,
        List<EpicProjectedItem> list);

    void invalidateEpic(long epicId, long projectId);
}
