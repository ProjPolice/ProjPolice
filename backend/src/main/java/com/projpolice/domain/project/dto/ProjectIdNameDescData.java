package com.projpolice.domain.project.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjectIdNameDescData {
    private long id;

    private String name;

    private String description;
}
