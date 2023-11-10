package com.projpolice.domain.user.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@RedisHash(value = "user-data", timeToLive = 30 * 24 * 60 * 60) // 30일
public class RefreshTokenRedisData {
    @Id
    String id;
    String refreshToken;
}
