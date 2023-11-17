package com.projpolice.global.redis;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.task.dto.TaskRelatedProjectionData;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

@Service
public class RedisDummyService implements RedisService {
    @Override
    public Optional<EpicDetailData> findEpicDetailById(long epicId) {
        return Optional.empty();
    }

    @Override
    public void saveEpicDetail(EpicDetailData data) {

    }

    @Override
    public Optional<List<EpicProjectedItem>> selectProjectEpicsWithDateRange(long projectId, LocalDate startDate,
        LocalDate endDate) {
        return Optional.empty();
    }

    @Override
    public void saveProjectEpicsWithDateRange(long projectId, LocalDate startDate, LocalDate endDate,
        List<EpicProjectedItem> list) {

    }

    @Override
    public Optional<ProjectDetailData> findProjectDetailById(long projectId) {
        return Optional.empty();
    }

    @Override
    public void saveProjectDetail(ProjectDetailData data) {

    }

    @Override
    public Optional<List<UserIdNameImgItem>> findProjectUserIdNameImgById(long projectId) {
        return Optional.empty();
    }

    @Override
    public void saveProjectUserIdNameImgList(long projectId, List<UserIdNameImgItem> list) {

    }

    @Override
    public void invalidateProject(long projectId) {

    }

    @Override
    public void invalidateProjectUser(long projectId) {

    }

    @Override
    public Optional<List<TaskRelatedProjectionData>> selectUserProjectsWithDateRange(long userId, LocalDate startDate,
        LocalDate endDate) {
        return Optional.empty();
    }

    @Override
    public void saveUserProjectsWithDateRange(long userId, LocalDate startDate, LocalDate endDate,
        List<TaskRelatedProjectionData> tasks) {

    }

    @Override
    public void invalidateTask(long taskId) {

    }

    @Override
    public void invalidateUser(long userId) {

    }
}
