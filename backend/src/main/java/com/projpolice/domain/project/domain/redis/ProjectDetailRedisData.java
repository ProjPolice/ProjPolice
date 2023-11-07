package com.projpolice.domain.project.domain.redis;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.user.dto.UserIdNameItem;

import lombok.Getter;

@Getter
@RedisHash(value = "project-detail", timeToLive = 2 * 60 * 60) // 2시간
public class ProjectDetailRedisData {

    @Id
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private UserIdNameItem owner;

    public ProjectDetailRedisData(ProjectDetailData data) {
        this.id = data.getId();
        this.name = data.getName();
        this.description = data.getDescription();
        this.startDate = data.getStartDate();
        this.endDate = data.getEndDate();
        this.owner = data.getOwner();
    }

    public ProjectDetailData to() {
        return ProjectDetailData.builder()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .startDate(this.startDate)
            .endDate(this.endDate)
            .owner(this.owner)
            .build();
    }
}
