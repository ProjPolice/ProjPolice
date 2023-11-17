package com.projpolice.domain.user.domain.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash(value = "user-data", timeToLive = 2 * 60 * 60) // 2시간
public class UserRedisData {
    @Id
    Long id;

    @Builder.Default
    List<String> formattedTaskId = new ArrayList<>();

    private UserRedisData() {
    }

    public UserRedisData(long userId) {
        this.id = userId;
        formattedTaskId = new ArrayList<>();
    }
}
