package com.projpolice.domain.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@RedisHash(value = "refresh-token", timeToLive = 4 * 60 * 60) // 4시간
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenEntity {
    @Id
    private String refresh;

    private Long userId;
}
