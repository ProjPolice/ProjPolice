package com.projpolice.domain.task.dto;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

public interface ProjectDetailProjection {
    Long getTaskId();

    String getTaskName();

    LocalDate getStartDate();

    LocalDate getEndDate();

    TaskStatus getTaskStatus();

    Long getUserId();

    String getUserName();

    String getUserImage();

    Long getEpicId();

    String getEpicName();

    String getFileName();

    String getFileUuid();
}
