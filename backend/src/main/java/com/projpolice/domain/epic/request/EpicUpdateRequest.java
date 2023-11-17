package com.projpolice.domain.epic.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class EpicUpdateRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
