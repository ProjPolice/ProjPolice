package com.projpolice.domain.epic.dto;

import com.projpolice.domain.epic.domain.rdb.Epic;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EpicBaseItem {
    private long id;
    private String name;

    public static EpicBaseItem from(Epic epic) {
        return EpicBaseItem.builder()
            .id(epic.getId())
            .name(epic.getName())
            .build();
    }
}
