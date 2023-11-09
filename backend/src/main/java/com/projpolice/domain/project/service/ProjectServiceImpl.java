package com.projpolice.domain.project.service;

import static com.projpolice.domain.user.service.JwtService.*;
import static com.projpolice.global.common.error.info.ExceptionInfo.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.resteasy.spi.UnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projpolice.domain.project.domain.rdb.Project;
import com.projpolice.domain.project.domain.rdb.UserProject;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.dto.ProjectIdNameDescData;
import com.projpolice.domain.project.repository.rdb.ProjectRepository;
import com.projpolice.domain.project.repository.rdb.UserProjectRepository;
import com.projpolice.domain.project.request.ProjectInsertRequest;
import com.projpolice.domain.project.request.ProjectModifyRequest;
import com.projpolice.domain.project.request.ProjectUserAddRequest;
import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.domain.user.repository.rdb.UserRepository;
import com.projpolice.domain.user.response.UserProjectPagingResponse;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.deletion.DeletionService;
import com.projpolice.global.common.error.exception.BadRequestException;
import com.projpolice.global.common.error.exception.UnAuthorizedException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;
import com.projpolice.global.redis.RedisService;

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
    private final RedisService redisService;
    private final DeletionService deletionService;

    /**
     * Retrieves the detailed information of a project based on its id.
     *
     * @param id the id of the project to be retrieved
     * @return the detailed information of the project
     * @throws BadRequestException if the project with the given id does not exist
     */
    @Override
    public ProjectDetailData selectProjectDetail(long id) {
        Optional<ProjectDetailData> cache = redisService.findProjectDetailById(id);
        if (cache.isPresent()) {
            return cache.get();
        }
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(INVALID_PROJECT)
        );
        ProjectDetailData data = ProjectDetailData.from(project);
        redisService.saveProjectDetail(data);
        projectAuthManager.checkProjectOwnershipOrThrow(project);

        return data;
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
        userProjectRepository.save(new UserProject(getLoggedUser(), project));

        ProjectDetailData data = ProjectDetailData.from(project);
        redisService.saveProjectDetail(data);

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
        projectAuthManager.checkProjectOwnershipOrThrow(id);
        deletionService.deleteProject(id);
        return new BaseIdItem(id);
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
        boolean modified = false;

        if (request.getName() != null) {
            project.setName(request.getName());
            modified = true;
        }

        if (request.getDescription() != null) {
            project.setDescription(request.getDescription());
            modified = true;
        }

        if (request.getStartDate() != null) {
            project.setStartDate(request.getStartDate());
            modified = true;
        }

        if (request.getEndDate() != null) {
            project.setEndDate(request.getEndDate());
            modified = true;
        }

        if (modified) {
            redisService.invalidateProject(id);
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
        Optional<List<UserIdNameImgItem>> cache = redisService.findProjectUserIdNameImgById(id);
        if (cache.isPresent()) {
            return cache.get();
        }

        List<UserIdNameImgItem> list = userProjectRepository.findUserByProjectId(id).stream()
            .map(user -> UserIdNameImgItem.builder()
                .id(user.getId())
                .name(user.getName())
                .image(user.getImage())
                .build()
            ).collect(Collectors.toList());

        redisService.saveProjectUserIdNameImgList(id, list);
        return list;
    }

    /**
     * Adds a user to the specified project.
     *
     * @param projectId the ID of the project to add the user to
     * @param request   the request object containing the email of the user to be added
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
        redisService.invalidateProjectUser(projectId);

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
     * @param userId    the ID of the user to be deleted
     * @return a {@link BaseIdItem} object representing the ID of the user that was deleted
     * @throws BadRequestException   if the user project is invalid
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
        redisService.invalidateProjectUser(projectId);

        return removedUserId;
    }

    /**
     * Retrieves a list of projects associated with the specified user.
     *
     * @param userId    the ID of the user
     * @param page      the page number to retrieve
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
