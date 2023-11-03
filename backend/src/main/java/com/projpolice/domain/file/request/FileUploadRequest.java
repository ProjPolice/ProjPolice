package com.projpolice.domain.file.request;

import lombok.Getter;

@Getter
public class FileUploadRequest {
    private String name;
    private String comment;
    private String uuid;
    private Integer version;
    private String extension;
}
