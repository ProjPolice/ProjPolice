package com.projpolice.global.redis;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.projpolice.domain.epic.domain.redis.EpicDetailRedisData;
import com.projpolice.domain.epic.domain.redis.ProjectEpicRedisItem;
import com.projpolice.domain.epic.domain.redis.ProjectEpicWithRangeRedisItem;
import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;
import com.projpolice.domain.epic.repository.redis.EpicDetailRedisRepository;
import com.projpolice.domain.epic.repository.redis.ProjectEpicRedisRepository;
import com.projpolice.domain.epic.repository.redis.ProjectEpicWithRangeRedisRepository;
import com.projpolice.domain.epic.service.EpicRedisInterface;
import com.projpolice.domain.project.repository.redis.ProjectDetailDataRedisRepository;
import com.projpolice.domain.project.repository.redis.ProjectUserIdNameImgListRedisRepository;

public class EpicRedisService extends ProjectRedisService implements EpicRedisInterface {

    private final EpicDetailRedisRepository epicDetailRedisRepository;

    private final ProjectEpicRedisRepository projectEpicRedisRepository;

    private final ProjectEpicWithRangeRedisRepository ProjectEpicWithRangeRedisRepository;

    @Autowired
    public EpicRedisService(ProjectDetailDataRedisRepository projectDetailDataRedisRepository,
        ProjectUserIdNameImgListRedisRepository projectUserIdNameImgListRedisRepository,
        ProjectEpicRedisRepository projectEpicRedisRepository, EpicDetailRedisRepository epicDetailRedisRepository,
        com.projpolice.domain.epic.repository.redis.ProjectEpicWithRangeRedisRepository projectEpicWithRangeRedisRepository) {
        super(projectDetailDataRedisRepository, projectUserIdNameImgListRedisRepository);
        this.projectEpicRedisRepository = projectEpicRedisRepository;
        this.epicDetailRedisRepository = epicDetailRedisRepository;
        ProjectEpicWithRangeRedisRepository = projectEpicWithRangeRedisRepository;
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
        super.invalidateProject(projectId);
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
