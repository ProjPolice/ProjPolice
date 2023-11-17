package com.projpolice.domain.task.service;

import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.request.TaskUpdateRequest;
import com.projpolice.domain.task.response.TaskUpdateResponse;

public interface TaskService {
    TaskDetailItem createTask(TaskCreateRequest taskCreateRequest);

    TaskUpdateResponse updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest);
}
