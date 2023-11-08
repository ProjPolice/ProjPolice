package com.projpolice.domain.epic.domain.redis;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.projpolice.domain.epic.dto.EpicProjectedItem;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@RedisHash(value = "project-epic-withRange", timeToLive = 2 * 60 * 60) // 2시간
@NoArgsConstructor
public class ProjectEpicWithRangeRedisItem {

    @Id
    private String id;

    private List<EpicProjectedItem> list;

    public static String id(long projectId, LocalDate startDate,
        LocalDate endDate) {
        return String.format("%d-%s-%s", projectId, startDate.toString(), endDate.toString());
    }

    public ProjectEpicWithRangeRedisItem(long projectId, LocalDate startDate,
        LocalDate endDate, List<EpicProjectedItem> list) {
        this.id = id(projectId, startDate, endDate);
        this.list = list;
    }
}
