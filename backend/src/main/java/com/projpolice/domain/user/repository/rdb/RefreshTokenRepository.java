package com.projpolice.domain.user.repository.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.user.domain.rdb.RefreshTokenEntity;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {

}
