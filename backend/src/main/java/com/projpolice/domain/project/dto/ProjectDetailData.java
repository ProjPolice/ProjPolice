package com.projpolice.domain.project.dto;

import java.time.LocalDate;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.user.domain.User;
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

    public static ProjectDetailData from(Project project) {
        User owner = project.getUser();
        return ProjectDetailData.builder()
            .id(project.getId())
            .name(project.getName())
            .description(project.getDescription())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .owner(UserIdNameItem.builder()
                .id(owner.getId())
                .name(owner.getName())
                .build())
            .build();
    }
}
