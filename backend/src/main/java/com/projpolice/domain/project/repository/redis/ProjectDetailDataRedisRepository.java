package com.projpolice.domain.project.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.projpolice.domain.project.domain.redis.ProjectDetailRedisData;

public interface ProjectDetailDataRedisRepository extends CrudRepository<ProjectDetailRedisData, Long> {
}
