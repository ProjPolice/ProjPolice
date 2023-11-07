package com.projpolice.domain.epic.domain.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "project-epic", timeToLive = 5 * 60) // 5분
public class ProjectEpicRedisItem {

    @Id
    private Long projectId; // projectId

    private List<String> formattedId;

    public ProjectEpicRedisItem(long id) {
        this.projectId = id;
        formattedId = new ArrayList<>();
    }
}
