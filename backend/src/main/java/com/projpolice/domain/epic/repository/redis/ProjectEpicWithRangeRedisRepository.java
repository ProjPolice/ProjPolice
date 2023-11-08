package com.projpolice.domain.epic.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.projpolice.domain.epic.domain.redis.ProjectEpicWithRangeRedisItem;

public interface ProjectEpicWithRangeRedisRepository extends CrudRepository<ProjectEpicWithRangeRedisItem, String> {
}
