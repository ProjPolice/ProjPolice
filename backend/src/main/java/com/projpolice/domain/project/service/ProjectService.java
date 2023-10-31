package com.projpolice.domain.project.service;

import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.request.ProjectInsertRequest;


public interface ProjectService {
    ProjectDetailData selectProjectDetail(long id);

    ProjectDetailData insertProject(ProjectInsertRequest request);
}
