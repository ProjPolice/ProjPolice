package com.projpolice.domain.task.dto;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.dto.FileNameUUID;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.global.common.base.BaseIdName;
import com.projpolice.global.common.meta.domain.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProjectTaskDetails {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private UserIdNameImgItem member;
    private BaseIdName epic;
    private List<FileDetailItem> file;

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
        file = new ArrayList<>();
        if (data.getFileId() != null) {
            file.add(FileDetailItem.from(data));
        }
    }

    public static List<ProjectTaskDetails> of(Collection<ProjectDetailProjection> list) {
        List<ProjectTaskDetails> result = new ArrayList<>();
        Map<Long, ProjectTaskDetails> map = new HashMap<>();
        for (ProjectDetailProjection projectDetailProjection : list) {
            long taskId = projectDetailProjection.getTaskId();
            ProjectTaskDetails task = map.get(taskId);
            if (task == null) {
                task = new ProjectTaskDetails(projectDetailProjection);
                map.put(taskId, task);
                result.add(task);
            } else {
                task.getFile().add(FileDetailItem.from(projectDetailProjection));
            }
        }
        for (ProjectTaskDetails projectTaskDetails : result) {
            projectTaskDetails.getFile().sort((o1, o2) -> -o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        }
        return result;
    }
}
