package com.projpolice.domain.file.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class FileBaseItem {
    private long id;
    private String name;
}
