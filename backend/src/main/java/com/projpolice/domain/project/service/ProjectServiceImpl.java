package com.projpolice.domain.project.service;

import static com.projpolice.domain.user.service.JwtService.*;
import static com.projpolice.global.common.error.info.ExceptionInfo.*;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.resteasy.spi.UnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.domain.UserProject;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.dto.ProjectIdNameDescData;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.project.repository.UserProjectRepository;
import com.projpolice.domain.project.request.ProjectInsertRequest;
import com.projpolice.domain.project.request.ProjectModifyRequest;
import com.projpolice.domain.project.request.ProjectUserAddRequest;
import com.projpolice.domain.user.domain.User;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.domain.user.repository.UserRepository;
import com.projpolice.domain.user.response.UserProjectPagingResponse;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.BadRequestException;
import com.projpolice.global.common.error.exception.UnAuthorizedException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;

import lombok.RequiredArgsConstructor;

/**
 * This class implements the ProjectService interface and provides the implementation
 * for the CRUD operations related to projects.
 */
@Service
@RequestMapping
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserProjectRepository userProjectRepository;

    private final UserRepository userRepository;

    private final ProjectAuthManager projectAuthManager;

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
            () -> new BadRequestException(INVALID_PROJECT)
        );

        projectAuthManager.checkProjectOwnershipOrThrow(project);

        return ProjectDetailData.from(project);
    }

    /**
     * Inserts a new project into the system.
     *
     * @param request the project insertion request containing the project details
     * @return the detailed information of the inserted project
     * @throws BadRequestException if the project insertion parameters are invalid
     */
    @Override
    @Transactional
    public ProjectDetailData insertProject(ProjectInsertRequest request) {
        if (request == null || request.getName() == null) {
            throw new BadRequestException(INVALID_PROJECT_INSERTION_PARAM);
        }

        Project project = Project.builder()
            .name(request.getName())
            .description(request.getDescription())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .user(getLoggedUser())
            .build();

        projectRepository.save(project);

        return ProjectDetailData.from(project);
    }

    /**
     * Deletes a project from the system.
     *
     * @param id the ID of the project to be deleted
     * @return the ID of the deleted project
     * @throws BadRequestException if the project ID is invalid
     */
    @Override
    @Transactional
    public BaseIdItem deleteProject(long id) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );
        projectAuthManager.checkProjectOwnershipOrThrow(project);

        BaseIdItem baseIdItem = new BaseIdItem(project.getId());
        projectRepository.delete(project);
        return baseIdItem;
    }

    /**
     * Modifies a project in the system.
     *
     * @param id      the ID of the project to be modified
     * @param request the modification request containing the new project details
     * @return the modified project details
     * @throws BadRequestException if the project ID is invalid
     */
    @Override
    @Transactional
    public ProjectDetailData modifyProject(long id, ProjectModifyRequest request) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );

        projectAuthManager.checkProjectOwnershipOrThrow(project);

        if (request.getName() != null) {
            project.setName(request.getName());
        }

        if (request.getDescription() != null) {
            project.setDescription(request.getDescription());
        }

        if (request.getStartDate() != null) {
            project.setStartDate(request.getStartDate());
        }

        if (request.getEndDate() != null) {
            project.setEndDate(request.getEndDate());
        }

        return ProjectDetailData.from(project);
    }

    /**
     * Retrieves a list of users associated with the specified project.
     *
     * @param id the ID of the project
     * @return a list of {@link UserIdNameImgItem} objects representing the users associated with the project
     * @throws UnauthorizedException if the user is not a member of the project
     */
    @Override
    public List<UserIdNameImgItem> listProjectUser(long id) {
        projectAuthManager.checkProjectMembershipOrThrow(id);
        return userProjectRepository.findUserByProjectId(id).stream()
            .map(user -> UserIdNameImgItem.builder()
                .id(user.getId())
                .name(user.getName())
                .image(user.getImage())
                .build()
            ).collect(Collectors.toList());
    }

    /**
     * Adds a user to the specified project.
     *
     * @param projectId       the ID of the project to add the user to
     * @param request         the request object containing the email of the user to be added
     * @return a {@link UserIdNameImgItem} object representing the user added to the project
     * @throws BadRequestException if the user or project is invalid
     */
    @Override
    @Transactional
    public UserIdNameImgItem addProjectUser(long projectId, ProjectUserAddRequest request) {
        projectAuthManager.checkProjectOwnershipOrThrow(projectId);

        User newUser = userRepository.findByEmail(request.getMemberEmail()).orElseThrow(
            () -> new BadRequestException(INVALID_USER)
        );
        Project project = projectRepository.findById(projectId).orElseThrow(
            () -> new BadRequestException(INVALID_PROJECT)
        );

        UserProject userProject = new UserProject(newUser, project);
        userProjectRepository.save(userProject);

        return UserIdNameImgItem.builder()
            .id(newUser.getId())
            .name(newUser.getName())
            .image(newUser.getImage())
            .build();
    }

    /**
     * Deletes a user from the specified project.
     *
     * @param projectId the ID of the project
     * @param userId the ID of the user to be deleted
     * @return a {@link BaseIdItem} object representing the ID of the user that was deleted
     * @throws BadRequestException if the user project is invalid
     * @throws UnAuthorizedException if the logged-in user is not the owner of the project
     */
    @Override
    @Transactional
    public BaseIdItem deleteProjectUser(long projectId, long userId) {
        projectAuthManager.checkProjectOwnershipOrThrow(projectId);

        UserProject userProject = userProjectRepository.findByProjectIdAndUserId(projectId, userId).orElseThrow(
            () -> new BadRequestException(INVALID_USER_PROJECT)
        );

        BaseIdItem removedUserId = new BaseIdItem(userProject.getUser().getId());
        userProjectRepository.delete(userProject);

        return removedUserId;
    }

    /**
     * Retrieves a list of projects associated with the specified user.
     *
     * @param userId the ID of the user
     * @param page the page number to retrieve
     * @param numOfRows the number of projects per page
     * @return a {@link UserProjectPagingResponse} object containing the list of projects and pagination information
     */
    @Override
    public UserProjectPagingResponse selectProjectOfUser(long userId, int page, int numOfRows) {
        Pageable pageable = PageRequest.of(page - 1, numOfRows);
        Page<Project> projects = projectRepository.findByUserId(userId, pageable);
        return UserProjectPagingResponse.builder()
            .projects(projects.stream()
                .map(project -> ProjectIdNameDescData.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .description(project.getDescription())
                    .build()
                ).collect(Collectors.toList())
            )
            .pages(projects.getTotalPages() + 1)
            .numOfRows(numOfRows)
            .build();
    }
}
