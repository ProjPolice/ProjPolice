package com.projpolice.domain.task.response;

import com.projpolice.domain.epic.dto.EpicBaseItem;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.task.dto.TaskBaseItem;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

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
