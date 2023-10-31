package com.projpolice.domain.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.user.domain.User;
import com.projpolice.domain.user.dto.UserIdNameItem;
import com.projpolice.global.common.error.exception.BadRequestException;
import com.projpolice.global.common.error.info.ExceptionInfo;

@Service
@RequestMapping
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;

    @Override
    public ProjectDetailData selectProjectDetail(long id) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );
        User owner = project.getUser();

        return ProjectDetailData.builder()
            .id(project.getId())
            .name(project.getName())
            .description(project.getDescription())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .owner(UserIdNameItem.builder()
                .id(owner.getId())
                .name(owner.getName())
                .build())
            .build();
    }
}
