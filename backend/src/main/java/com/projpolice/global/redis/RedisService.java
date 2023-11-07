package com.projpolice.global.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projpolice.domain.epic.repository.redis.EpicDetailRedisRepository;
import com.projpolice.domain.epic.repository.redis.ProjectEpicRedisRepository;
import com.projpolice.domain.project.repository.redis.ProjectDetailDataRedisRepository;
import com.projpolice.domain.project.repository.redis.ProjectUserIdNameImgListRedisRepository;

@Service
public class RedisService extends EpicRedisService {

    @Autowired
    public RedisService(ProjectDetailDataRedisRepository projectDetailDataRedisRepository,
        ProjectUserIdNameImgListRedisRepository projectUserIdNameImgListRedisRepository,
        ProjectEpicRedisRepository projectEpicRedisRepository,
        EpicDetailRedisRepository epicDetailRedisRepository,
        com.projpolice.domain.epic.repository.redis.ProjectEpicWithRangeRedisRepository projectEpicWithRangeRedisRepository) {
        super(projectDetailDataRedisRepository, projectUserIdNameImgListRedisRepository, projectEpicRedisRepository,
            epicDetailRedisRepository, projectEpicWithRangeRedisRepository);
    }
}
