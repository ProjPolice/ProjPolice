package com.projpolice.domain.project.dto;

import java.time.LocalDate;

import com.projpolice.domain.user.dto.UserIdNameItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailData {
    private long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private UserIdNameItem owner;
}
