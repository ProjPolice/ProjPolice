package com.projpolice.domain.epic.domain.redis;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.projpolice.domain.epic.dto.EpicDetailData;

import lombok.Getter;

@Getter
@RedisHash(value = "epic-detail", timeToLive = 2 * 60 * 60) // 2시간
public class EpicDetailRedisData {
    @Id
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long projectId;

    public EpicDetailRedisData(EpicDetailData data) {
        this.id = data.getId();
        this.name = data.getName();
        this.description = data.getDescription();
        this.startDate = data.getStartDate();
        this.endDate = data.getEndDate();
        this.projectId = data.getProjectId();
    }

    public EpicDetailData of() {
        return EpicDetailData.builder()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .startDate(this.startDate)
            .endDate(this.endDate)
            .projectId(this.projectId)
            .build();
    }
}
