package com.projpolice.domain.epic.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EpicProjectedItem {
    long id;
    String name;
    LocalDate startDate;
    LocalDate endDate;

    @Builder.Default
    List<TaskProjectedItem> tasks = new ArrayList<>();
}
