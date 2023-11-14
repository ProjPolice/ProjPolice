package com.projpolice.global.redis;

import com.projpolice.domain.epic.service.EpicRedisInterface;
import com.projpolice.domain.project.service.ProjectRedisInterface;
import com.projpolice.domain.task.service.TaskRedisInterface;
import com.projpolice.domain.user.service.UserRedisInterface;

public interface RedisService
    extends ProjectRedisInterface, EpicRedisInterface, TaskRedisInterface, UserRedisInterface {
}
