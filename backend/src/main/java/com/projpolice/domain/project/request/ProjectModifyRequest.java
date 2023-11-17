package com.projpolice.domain.project.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModifyRequest {
    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
}
