package com.projpolice.domain.user.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.user.domain.redis.UserRedisData;

@Repository
public interface UserDataRedisRepository extends CrudRepository<UserRedisData, Long> {
}
