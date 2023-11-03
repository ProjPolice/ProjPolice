package com.projpolice.domain.epic.dto;

import java.time.LocalDate;
import java.util.List;

// 작동 안 됨... 우선은 넣어는 둠
@Deprecated
public interface EpicProjectionData {
    Long getId();

    String getName();

    LocalDate getStartDate();

    LocalDate getEndDate();

    List<TaskProjectionData> getTasks();

    interface TaskProjectionData {
        Long getTaskId();

        String getTaskName();

        LocalDate getTaskStartDate();

        LocalDate getTaskEndDate();
    }
}
