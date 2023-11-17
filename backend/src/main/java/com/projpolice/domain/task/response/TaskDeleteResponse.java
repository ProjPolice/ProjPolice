package com.projpolice.domain.task.response;

import com.projpolice.domain.task.domain.rdb.Task;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskDeleteResponse {
    private Long id;
    public static TaskDeleteResponse from(Task task) {
        return TaskDeleteResponse.builder()
            .id(task.getId())
            .build();
    }
}
