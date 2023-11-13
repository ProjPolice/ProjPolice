package com.projpolice.domain.file.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileNameUUID {
    private String name;
    private String uuid;
}
