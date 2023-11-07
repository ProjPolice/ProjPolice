package com.projpolice.domain.task.response;

import java.util.List;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.task.dto.TaskBaseItem;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TaskGetResponse extends TaskBaseItem {
    private UserIdNameImgItem user;
    private EpicDetailData epic;
    private List<FileDetailItem> files;

    public static TaskGetResponse from(Task task, List<FileDetailItem> files) {
        return TaskGetResponse.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .status(task.getStatus().name())
            .startDate(task.getStartDate())
            .endDate(task.getEndDate())
            .user(UserIdNameImgItem.from(task.getUser()))
            .epic(EpicDetailData.from(task.getEpic()))
            .files(files)
            .build();
    }
}
