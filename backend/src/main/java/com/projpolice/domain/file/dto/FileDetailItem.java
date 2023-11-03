package com.projpolice.domain.file.dto;

import com.projpolice.domain.file.domain.File;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDetailItem {
    private Long id;
    private String name;
    private String comment;
    private String uuid;
    private Integer version;
    private String extension;
    private Long taskId;

    public static FileDetailItem from(File file) {
        return FileDetailItem.builder()
            .id(file.getId())
            .name(file.getName())
            .comment(file.getComment())
            .version(file.getVersion())
            .extension(file.getExtension())
            .taskId(file.getTask().getId())
            .build();
    }
}
