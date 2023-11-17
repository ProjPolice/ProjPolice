package com.projpolice.domain.task.dto;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TaskDetailItem extends TaskBaseItem {
    private UserIdNameImgItem user;
    private EpicDetailData epic;

    public static TaskDetailItem from(Task task) {
        return TaskDetailItem.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .status(task.getStatus().name())
            .startDate(task.getStartDate())
            .endDate(task.getEndDate())
            .user(UserIdNameImgItem.from(task.getUser()))
            .epic(EpicDetailData.from(task.getEpic()))
            .build();
    }
}
