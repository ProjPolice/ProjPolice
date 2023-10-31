package com.projpolice.domain.project.service;

import com.projpolice.domain.project.dto.ProjectDetailData;

public interface ProjectService {
    ProjectDetailData selectProjectDetail(long id);
}
