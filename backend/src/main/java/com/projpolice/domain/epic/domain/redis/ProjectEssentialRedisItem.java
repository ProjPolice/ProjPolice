package com.projpolice.domain.epic.domain.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "project-essential", timeToLive = 5 * 60) // 5분
public class ProjectEssentialRedisItem {

    @Id
    private Long projectId; // projectId

    private List<String> formattedEpicProjectionId;

    private List<Long> epicIds;

    private List<Long> taskIds;

    private ProjectEssentialRedisItem() {
    }

    public ProjectEssentialRedisItem(long projectId) {
        this.projectId = projectId;
        formattedEpicProjectionId = new ArrayList<>();
        epicIds = new ArrayList<>();
        taskIds = new ArrayList<>();
    }
}
