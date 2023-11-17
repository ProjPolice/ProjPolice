package com.projpolice.domain.task.dto;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TaskBaseItem {
    private long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status = TaskStatus.TODO.name();
}
