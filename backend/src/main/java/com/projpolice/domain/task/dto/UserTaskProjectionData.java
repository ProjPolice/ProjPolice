package com.projpolice.domain.task.dto;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

public interface UserTaskProjectionData {

    Long getTaskId();

    String getTaskName();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Long getEpicId();

    String getEpicName();

    Long getProjectId();

    String getProjectName();

    Long getFileId();

    String getFileName();

    TaskStatus getTaskStatus();

    Long getUserId();
}
