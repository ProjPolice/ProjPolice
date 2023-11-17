package com.projpolice.domain.project.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.redis.ProjectUserIdNameImgListRedisData;

@Repository
public interface ProjectUserIdNameImgListRedisRepository
    extends CrudRepository<ProjectUserIdNameImgListRedisData, Long> {
}
