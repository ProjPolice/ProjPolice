package com.projpolice.domain.epic.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskProjectedItem {
    long id;
    String name;
    LocalDate startDate;
    LocalDate endDate;
}
