package com.projpolice.domain.user.service;

public interface UserRedisInterface {
    void invalidateUser(long userId);
}
