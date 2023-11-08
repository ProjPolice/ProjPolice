package com.projpolice.global.redis;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projpolice.domain.epic.domain.redis.EpicDetailRedisData;
import com.projpolice.domain.epic.domain.redis.ProjectEpicRedisItem;
import com.projpolice.domain.epic.domain.redis.ProjectEpicWithRangeRedisItem;
import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;
import com.projpolice.domain.epic.repository.redis.EpicDetailRedisRepository;
import com.projpolice.domain.epic.repository.redis.ProjectEpicRedisRepository;
import com.projpolice.domain.epic.repository.redis.ProjectEpicWithRangeRedisRepository;
import com.projpolice.domain.epic.service.EpicRedisInterface;
import com.projpolice.domain.project.domain.redis.ProjectDetailRedisData;
import com.projpolice.domain.project.domain.redis.ProjectUserIdNameImgListRedisData;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.repository.redis.ProjectDetailDataRedisRepository;
import com.projpolice.domain.project.repository.redis.ProjectUserIdNameImgListRedisRepository;
import com.projpolice.domain.project.service.ProjectRedisInterface;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService implements ProjectRedisInterface, EpicRedisInterface {
    private final ProjectDetailDataRedisRepository projectDetailDataRedisRepository;
    private final ProjectUserIdNameImgListRedisRepository projectUserIdNameImgListRedisRepository;
    private final EpicDetailRedisRepository epicDetailRedisRepository;
    private final ProjectEpicRedisRepository projectEpicRedisRepository;
    private final ProjectEpicWithRangeRedisRepository ProjectEpicWithRangeRedisRepository;

    @Override
    public Optional<ProjectDetailData> findProjectDetailById(long id) {
        Optional<ProjectDetailRedisData> cache = projectDetailDataRedisRepository.findById(id);
        return cache.map(ProjectDetailRedisData::of);
    }

    @Override
    public void saveProjectDetail(ProjectDetailData data) {
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
    public void invalidateProjectUser(long id) {
        projectUserIdNameImgListRedisRepository.deleteById(id);
    }

    @Override
    public Optional<EpicDetailData> findEpicDetailById(long epicId) {
        Optional<EpicDetailRedisData> cache = epicDetailRedisRepository.findById(epicId);

        if (cache.isPresent()) {
            return cache.map(EpicDetailRedisData::of);
        }

        return Optional.empty();
    }

    @Override
    public void saveEpicDetail(EpicDetailData data) {
        epicDetailRedisRepository.save(new EpicDetailRedisData(data));
    }

    @Override
    public Optional<List<EpicProjectedItem>> selectProjectEpicsWithDateRange(long projectId, LocalDate startDate,
        LocalDate endDate) {
        String id = ProjectEpicWithRangeRedisItem.id(projectId, startDate, endDate);
        Optional<ProjectEpicWithRangeRedisItem> cache = ProjectEpicWithRangeRedisRepository.findById(id);
        return cache.map(ProjectEpicWithRangeRedisItem::getList);

    }

    @Override
    public void saveProjectEpicsWithDateRange(long projectId, LocalDate startDate, LocalDate endDate,
        List<EpicProjectedItem> list) {
        Optional<ProjectEpicRedisItem> projectRedisCache = projectEpicRedisRepository.findById(projectId);
        String formattedId = ProjectEpicWithRangeRedisItem.id(projectId, startDate, endDate);
        ProjectEpicRedisItem project;
        if (projectRedisCache.isPresent()) {
            project = projectRedisCache.get();
            project.getFormattedId().add(formattedId);
        } else {
            project = new ProjectEpicRedisItem(projectId);
            project.getFormattedId().add(formattedId);
        }
        projectEpicRedisRepository.save(project);
        ProjectEpicWithRangeRedisRepository.save(
            new ProjectEpicWithRangeRedisItem(projectId, startDate, endDate, list));
    }

    @Override
    public void invalidateProject(long projectId) {
        projectDetailDataRedisRepository.deleteById(projectId);
        projectUserIdNameImgListRedisRepository.deleteById(projectId);
        Optional<ProjectEpicRedisItem> projectRedisCache = projectEpicRedisRepository.findById(projectId);
        if (projectRedisCache.isEmpty()) {
            return;
        }
        ProjectEpicRedisItem project = projectRedisCache.get();
        ProjectEpicWithRangeRedisRepository.deleteAllById(project.getFormattedId());
        projectEpicRedisRepository.delete(project);
    }

    @Override
    public void invalidateEpic(long epicId) {
        epicDetailRedisRepository.deleteById(epicId);
    }

}
