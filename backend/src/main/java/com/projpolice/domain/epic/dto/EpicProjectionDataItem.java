package com.projpolice.domain.epic.dto;

import java.time.LocalDate;

public interface EpicProjectionDataItem {
    Long getEpicId();

    String getEpicName();

    LocalDate getEpicStartDate();

    LocalDate getEpicEndDate();

    Long getTaskId();

    String getTaskName();

    LocalDate getTaskStartDate();

    LocalDate getTaskEndDate();
}
