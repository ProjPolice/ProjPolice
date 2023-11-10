package com.projpolice.domain.task.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.task.domain.redis.UserTaskRedisData;

@Repository
public interface UserTaskRedisRepository extends CrudRepository<UserTaskRedisData, String> {
}
