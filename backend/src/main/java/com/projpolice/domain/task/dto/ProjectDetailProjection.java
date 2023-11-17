package com.projpolice.domain.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    Long getFileId();
    String getFileName();

    String getFileComment();
    Integer getFileVersion();
    String getFileExtension();
    LocalDateTime getFileCreatedAt();
}
