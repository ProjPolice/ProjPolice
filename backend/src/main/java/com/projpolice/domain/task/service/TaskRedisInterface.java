package com.projpolice.domain.task.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.projpolice.domain.task.dto.TaskRelatedProjectionData;

public interface TaskRedisInterface {

    Optional<List<TaskRelatedProjectionData>> selectUserProjectsWithDateRange(long userId, LocalDate startDate,
        LocalDate endDate);

    void saveUserProjectsWithDateRange(long userId, LocalDate startDate, LocalDate endDate,
        List<TaskRelatedProjectionData> tasks);

    void invalidateTask(long taskId);
}
