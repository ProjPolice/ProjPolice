package com.projpolice.domain.project.service;

import static com.projpolice.domain.project.domain.Project.*;
import static com.projpolice.global.common.error.info.ExceptionInfo.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.project.request.ProjectInsertRequest;
import com.projpolice.global.common.error.exception.BadRequestException;
import com.projpolice.global.common.error.info.ExceptionInfo;

@Service
@RequestMapping
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;

    /**
     * Retrieves the detailed information of a project based on its id.
     *
     * @param id the id of the project to be retrieved
     * @return the detailed information of the project
     * @throws BadRequestException if the project with the given id does not exist
     */
    @Override
    public ProjectDetailData selectProjectDetail(long id) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );
        return toDetailData(project);
    }

    /**
     * Inserts a new project into the system.
     *
     * @param request the project insertion request containing the project details
     * @return the detailed information of the inserted project
     * @throws BadRequestException if the project insertion parameters are invalid
     */
    @Override
    public ProjectDetailData insertProject(ProjectInsertRequest request) {
        if (request == null || request.getName() == null) {
            throw new BadRequestException(INVALID_PROJECT_INSERTION_PARAM);
        }

        Project project = Project.builder()
            .name(request.getName())
            .description(request.getDescription())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            // TODO: insert user when user context holder is ready
            .user(null)
            .build();

        return toDetailData(project);
    }
}
