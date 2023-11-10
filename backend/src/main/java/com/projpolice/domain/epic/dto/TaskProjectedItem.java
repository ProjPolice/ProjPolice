package com.projpolice.domain.epic.dto;

import java.time.LocalDate;

import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskProjectedItem {
    long id;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    TaskStatus status;
    long userId;
}
