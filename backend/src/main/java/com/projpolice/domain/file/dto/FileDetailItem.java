package com.projpolice.domain.file.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projpolice.domain.file.domain.File;
import com.projpolice.domain.task.dto.ProjectDetailProjection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDetailItem {
    private Long id;
    private String name;
    private String comment;
    @JsonIgnore
    private String uuid;
    private Integer version;
    private String extension;
    private Long taskId;
    private LocalDateTime createdAt;

    public static FileDetailItem from(File file) {
        return FileDetailItem.builder()
            .id(file.getId())
            .name(file.getName())
            .uuid(file.getUuid())
            .comment(file.getComment())
            .version(file.getVersion())
            .extension(file.getExtension())
            .taskId(file.getTask().getId())
            .createdAt(file.getCreatedAt())
            .build();
    }

    public static FileDetailItem from(ProjectDetailProjection detail){
        return FileDetailItem.builder()
            .id(detail.getFileId())
            .name(detail.getFileName())
            .comment(detail.getFileComment())
            .version(detail.getFileVersion())
            .extension(detail.getFileExtension())
            .taskId(detail.getTaskId())
            .build();
    }
}
