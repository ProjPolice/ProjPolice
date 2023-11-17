package com.projpolice.domain.epic.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class EpicCreateRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long projectId;
}
