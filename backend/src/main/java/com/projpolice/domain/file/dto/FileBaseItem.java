package com.projpolice.domain.file.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileBaseItem {
    private long id;
    private String name;
}
