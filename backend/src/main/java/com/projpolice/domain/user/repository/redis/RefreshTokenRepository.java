package com.projpolice.domain.user.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.user.domain.redis.RefreshTokenRedisData;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenRedisData, String> {

}
