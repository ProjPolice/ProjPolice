package com.projpolice.domain.task.dto;

import java.time.LocalDate;

import com.projpolice.domain.file.dto.FileNameUUID;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.global.common.base.BaseIdName;
import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectTaskDetails {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private UserIdNameImgItem member;
    private BaseIdName epic;
    private FileNameUUID file;

    public ProjectTaskDetails(ProjectDetailProjection data) {
        id = data.getTaskId();
        name = data.getTaskName();
        startDate = data.getStartDate();
        endDate = data.getEndDate();
        status = data.getTaskStatus();
        member = UserIdNameImgItem.builder()
            .id(data.getUserId())
            .name(data.getUserName())
            .image(data.getUserImage())
            .build();
        epic = new BaseIdName(data.getEpicId(), data.getEpicName());
        file = FileNameUUID.builder()
            .name(data.getFileName())
            .uuid(data.getFileUuid())
            .build();
    }
}
