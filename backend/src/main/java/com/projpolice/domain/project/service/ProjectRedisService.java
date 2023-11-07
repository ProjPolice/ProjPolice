package com.projpolice.domain.project.service;

import java.util.List;
import java.util.Optional;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.user.dto.UserIdNameImgItem;

public interface ProjectRedisService {

    Optional<ProjectDetailData> findProjectDetailCacheById(long id);

    void saveProjectDetailCache(ProjectDetailData data);

    Optional<List<UserIdNameImgItem>> findProjectUserIdNameImgById(long id);

    void saveProjectUserIdNameImgList(long id, List<UserIdNameImgItem> list);

    void invalidateProject(long id);

    void invalidateProjectUser(long id);
}
