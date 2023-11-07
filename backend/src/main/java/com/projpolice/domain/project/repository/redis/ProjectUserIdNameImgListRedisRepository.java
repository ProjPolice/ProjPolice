package com.projpolice.domain.project.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.projpolice.domain.project.domain.redis.ProjectUserIdNameImgListRedisData;

public interface ProjectUserIdNameImgListRedisRepository
    extends CrudRepository<ProjectUserIdNameImgListRedisData, Long> {
}
