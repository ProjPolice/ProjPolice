package com.projpolice.domain.user.repository.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.user.domain.rdb.RefreshTokenEntity;
import com.projpolice.domain.user.domain.redis.RefreshTokenRedisData;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenRedisData, String> {

}
