package com.projpolice.domain.epic.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.epic.domain.redis.EpicDetailRedisData;

@Repository
public interface EpicDetailRedisRepository extends CrudRepository<EpicDetailRedisData, Long> {
}
