package com.projpolice.domain.file.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadRequest {
    private String comment;
    private Integer version;
    private MultipartFile file;
}
