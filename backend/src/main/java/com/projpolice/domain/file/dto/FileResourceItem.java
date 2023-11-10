package com.projpolice.domain.file.dto;

import org.springframework.core.io.Resource;

import com.projpolice.domain.file.domain.File;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class FileResourceItem extends FileBaseItem {
    Resource resource;

    public static FileResourceItem of(File file, Resource resource) {
        return FileResourceItem.builder()
            .id(file.getId())
            .name(file.getName())
            .resource(resource)
            .build();
    }
}
