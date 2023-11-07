package com.projpolice.domain.project.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.redis.ProjectDetailRedisData;

@Repository
public interface ProjectDetailDataRedisRepository extends CrudRepository<ProjectDetailRedisData, Long> {
}
