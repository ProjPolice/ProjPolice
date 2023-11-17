package com.projpolice.domain.user.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@RedisHash(value = "user-data-refresh", timeToLive = 5 * 24 * 60 * 60) // 5일
public class RefreshTokenRedisData {
    @Id
    private String id;
    private String refreshToken;
}
