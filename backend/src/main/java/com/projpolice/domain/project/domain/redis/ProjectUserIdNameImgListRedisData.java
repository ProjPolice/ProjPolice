package com.projpolice.domain.project.domain.redis;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@RedisHash(value = "project-user-id-name-img", timeToLive = 2 * 60 * 60) // 2시간
@AllArgsConstructor
public class ProjectUserIdNameImgListRedisData {

    @Id
    private Long id;

    private List<UserIdNameImgItem> list;
}
