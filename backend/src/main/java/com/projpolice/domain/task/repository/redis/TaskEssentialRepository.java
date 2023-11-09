package com.projpolice.domain.task.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.task.domain.redis.TaskEssentialRedisData;

@Repository
public interface TaskEssentialRepository extends CrudRepository<TaskEssentialRedisData, Long> {
}
