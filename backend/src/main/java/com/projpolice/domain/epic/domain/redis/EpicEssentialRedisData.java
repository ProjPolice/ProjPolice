package com.projpolice.domain.epic.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@RedisHash(value = "epic-essential", timeToLive = 2 * 60 * 60) // 2시간
@NoArgsConstructor
@AllArgsConstructor
public class EpicEssentialRedisData {
    @Id
    Long epicId;

    Long projectId;
}
