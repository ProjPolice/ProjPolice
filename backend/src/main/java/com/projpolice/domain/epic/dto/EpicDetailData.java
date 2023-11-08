package com.projpolice.domain.epic.dto;

import java.time.LocalDate;

import com.projpolice.domain.epic.domain.rdb.Epic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EpicDetailData {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long projectId;

    public static EpicDetailData from(Epic epic) {
        return EpicDetailData.builder()
            .id(epic.getId())
            .name(epic.getName())
            .description(epic.getDescription())
            .startDate(epic.getStartDate())
            .endDate(epic.getEndDate())
            .projectId(epic.getProject().getId())
            .build();
    }
}
