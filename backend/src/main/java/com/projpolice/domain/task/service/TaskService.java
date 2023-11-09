package com.projpolice.domain.task.service;

import java.time.LocalDate;
import java.util.List;

import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.dto.TaskRelatedProjectionData;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.request.TaskUpdateRequest;
import com.projpolice.domain.task.response.TaskGetResponse;
import com.projpolice.domain.task.response.TaskUpdateResponse;
import com.projpolice.global.common.base.BaseIdItem;

public interface TaskService {
    TaskDetailItem createTask(TaskCreateRequest taskCreateRequest);

    TaskUpdateResponse updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest);

    BaseIdItem deleteTask(Long taskId);

    TaskGetResponse getTask(Long taskId);

    List<TaskRelatedProjectionData> selectUserTaskRelatedDataWithRange(LocalDate startDate,
        LocalDate endDate);
}
