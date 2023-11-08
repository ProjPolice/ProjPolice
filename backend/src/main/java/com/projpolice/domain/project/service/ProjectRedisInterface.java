package com.projpolice.domain.project.service;

import java.util.List;
import java.util.Optional;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

public interface ProjectRedisInterface {

    Optional<ProjectDetailData> findProjectDetailById(long projectId);

    void saveProjectDetail(ProjectDetailData data);

    Optional<List<UserIdNameImgItem>> findProjectUserIdNameImgById(long projectId);

    void saveProjectUserIdNameImgList(long projectId, List<UserIdNameImgItem> list);

    void invalidateProject(long projectId);

    void invalidateProjectUser(long projectId);
}
