package com.projpolice.domain.task.response;

import com.projpolice.domain.epic.dto.EpicBaseItem;
import com.projpolice.domain.task.domain.rdb.Task;
import com.projpolice.domain.task.dto.TaskBaseItem;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TaskUpdateResponse extends TaskBaseItem {
    private UserIdNameImgItem user;
    private EpicBaseItem epic;

    public static TaskUpdateResponse of(Task task, String imageUrl) {
        return TaskUpdateResponse.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .status(task.getStatus().name())
            .startDate(task.getStartDate())
            .endDate(task.getEndDate())
            .user(UserIdNameImgItem.of(task.getUser(), imageUrl))
            .epic(EpicBaseItem.from(task.getEpic()))
            .build();
    }
}
