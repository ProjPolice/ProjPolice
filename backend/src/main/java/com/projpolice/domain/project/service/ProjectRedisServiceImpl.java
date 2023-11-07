package com.projpolice.domain.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projpolice.domain.project.domain.redis.ProjectDetailRedisData;
import com.projpolice.domain.project.domain.redis.ProjectUserIdNameImgListRedisData;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.repository.redis.ProjectDetailDataRedisRepository;
import com.projpolice.domain.project.repository.redis.ProjectUserIdNameImgListRedisRepository;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectRedisServiceImpl implements ProjectRedisService {

    private final ProjectDetailDataRedisRepository projectDetailDataRedisRepository;

    private final ProjectUserIdNameImgListRedisRepository projectUserIdNameImgListRedisRepository;

    @Override
    public Optional<ProjectDetailData> findProjectDetailCacheById(long id) {
        Optional<ProjectDetailRedisData> cache = projectDetailDataRedisRepository.findById(id);
        return cache.map(ProjectDetailRedisData::to);
    }

    @Override
    public void saveProjectDetailCache(ProjectDetailData data) {
        projectDetailDataRedisRepository.save(new ProjectDetailRedisData(data));
    }

    @Override
    public Optional<List<UserIdNameImgItem>> findProjectUserIdNameImgById(long id) {
        Optional<ProjectUserIdNameImgListRedisData> cache = projectUserIdNameImgListRedisRepository.findById(id);
        if (cache.isEmpty()) {
            return Optional.empty();
        }
        return cache.map(ProjectUserIdNameImgListRedisData::getList);
    }

    @Override
    public void saveProjectUserIdNameImgList(long id, List<UserIdNameImgItem> list) {
        projectUserIdNameImgListRedisRepository.save(new ProjectUserIdNameImgListRedisData(id, list));
    }

    @Override
    public void invalidateProject(long id) {
        projectDetailDataRedisRepository.deleteById(id);
        projectUserIdNameImgListRedisRepository.deleteById(id);
    }

    @Override
    public void invalidateProjectUser(long id) {
        projectUserIdNameImgListRedisRepository.deleteById(id);
    }
}
