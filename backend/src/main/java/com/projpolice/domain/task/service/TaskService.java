package com.projpolice.domain.task.service;

import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.request.TaskCreateRequest;

public interface TaskService {
    TaskDetailItem createTask(TaskCreateRequest taskCreateRequest);
}
