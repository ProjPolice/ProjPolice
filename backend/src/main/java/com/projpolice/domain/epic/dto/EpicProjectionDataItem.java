package com.projpolice.domain.epic.dto;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

public interface EpicProjectionDataItem {
    Long getEpicId();

    String getEpicName();

    LocalDate getEpicStartDate();

    LocalDate getEpicEndDate();

    Long getTaskId();

    String getTaskName();

    LocalDate getTaskStartDate();

    LocalDate getTaskEndDate();

    TaskStatus getTaskStatus();

    Long getUserId();
}
