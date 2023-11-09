package com.projpolice.domain.task.dto;

import java.time.LocalDate;

import com.projpolice.global.common.base.BaseIdName;
import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRelatedProjectionData {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private BaseIdName epic;
    private BaseIdName project;
    private BaseIdName file;

    public TaskRelatedProjectionData(UserTaskProjectionData data) {
        this.id = data.getTaskId();
        this.name = data.getTaskName();
        this.startDate = data.getStartDate();
        this.endDate = data.getEndDate();
        this.status = data.getTaskStatus();
        if (data.getEpicId() != null) {
            this.epic = new BaseIdName(
                data.getEpicId(),
                data.getEpicName()
            );
        }
        if (data.getProjectId() != null) {
            this.project = new BaseIdName(
                data.getProjectId(),
                data.getProjectName()
            );
        }
        if (data.getFileId() != null) {
            this.file = new BaseIdName(
                data.getFileId(),
                data.getFileName()
            );
        }
    }
}
