package com.projpolice.domain.task.request;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.Getter;

@Getter
public class TaskCreateRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status = TaskStatus.TODO;
    private Long userId;
    private Long epicId;
}
