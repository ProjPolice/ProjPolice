package com.projpolice.domain.project.service;

import java.util.List;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.request.ProjectInsertRequest;
import com.projpolice.domain.project.request.ProjectModifyRequest;
import com.projpolice.domain.project.request.ProjectUserAddRequest;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.global.common.base.BaseIdItem;

/**
 * ProjectService interface provides methods to interact with project data.
 */
public interface ProjectService {
    ProjectDetailData selectProjectDetail(long id);

    ProjectDetailData insertProject(ProjectInsertRequest request);

    BaseIdItem deleteProject(long id);

    ProjectDetailData modifyProject(long id, ProjectModifyRequest request);

    List<UserIdNameImgItem> listProjectUser(long id);

    UserIdNameImgItem addProjectUser(long projectId, ProjectUserAddRequest request);
}
