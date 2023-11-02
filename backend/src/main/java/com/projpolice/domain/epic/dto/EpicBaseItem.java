package com.projpolice.domain.epic.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EpicBaseItem {
    private long id;
    private String name;
}
