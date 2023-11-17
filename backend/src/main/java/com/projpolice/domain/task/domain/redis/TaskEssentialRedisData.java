package com.projpolice.domain.task.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@RedisHash(value = "task-essential", timeToLive = 2 * 60 * 60) // 2시간
@NoArgsConstructor
@AllArgsConstructor
public class TaskEssentialRedisData {
    @Id
    Long taskId;

    Long epicId;

    Long projectId;

    Long userId;
}
