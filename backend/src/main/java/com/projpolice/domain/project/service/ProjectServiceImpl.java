package com.projpolice.domain.project.service;

import static com.projpolice.domain.user.service.JwtService.*;
import static com.projpolice.global.common.error.info.ExceptionInfo.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.domain.UserProject;
import com.projpolice.domain.project.dto.ProjectDetailData;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.project.repository.UserProjectRepository;
import com.projpolice.domain.project.request.ProjectInsertRequest;
import com.projpolice.domain.project.request.ProjectModifyRequest;
import com.projpolice.domain.project.request.ProjectUserAddRequest;
import com.projpolice.domain.user.domain.User;
import com.projpolice.domain.user.dto.UserIdNameImgItem;
import com.projpolice.domain.user.repository.UserRepository;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.BadRequestException;
import com.projpolice.global.common.error.exception.UnAuthorizedException;
import com.projpolice.global.common.error.info.ExceptionInfo;

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

        /*
        TODO: implement sharing
         */
        List<User> users = userProjectRepository.findByProjectId(id);
        checkMembership(users, getLoggedUser());

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
    public BaseIdItem deleteProject(long id) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );

        User loggedUser = getLoggedUser();
        checkOwnership(loggedUser, project);

        BaseIdItem baseIdItem = new BaseIdItem(project.getId());
        projectRepository.delete(project);
        return baseIdItem;
    }

    /**
     * Modifies a project in the system.
     *
     * @param id the ID of the project to be modified
     * @param request the modification request containing the new project details
     * @return the modified project details
     * @throws BadRequestException if the project ID is invalid
     */
    @Override
    public ProjectDetailData modifyProject(long id, ProjectModifyRequest request) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );

        User loggedUser = getLoggedUser();

        // TODO: determine whether only the owner can modify or member of project can modify or not.
        checkOwnership(loggedUser, project);

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
     */
    @Override
    public List<UserIdNameImgItem> listProjectUser(long id) {
        List<User> users = userProjectRepository.findByProjectId(id);

        User loggedUser = getLoggedUser();
        checkMembership(users, loggedUser);

        return users.stream()
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
    public UserIdNameImgItem addProjectUser(long projectId, ProjectUserAddRequest request) {
        User newUser = userRepository.findByEmail(request.getMemberEmail()).orElseThrow(
            () -> new BadRequestException(INVALID_USER)
        );
        Project project = projectRepository.findById(projectId).orElseThrow(
            () -> new BadRequestException(INVALID_PROJECT)
        );

        checkOwnership(getLoggedUser(), project);

        UserProject userProject = new UserProject(newUser, project);
        userProjectRepository.save(userProject);

        return UserIdNameImgItem.builder()
            .id(newUser.getId())
            .name(newUser.getName())
            .image(newUser.getImage())
            .build();
    }

    /**
     * Checks if the logged-in user is the owner of the specified project.
     * If the logged-in user is not the owner, an exception is thrown.
     *
     * @param loggedUser the logged-in user
     * @param project the project to be checked for ownership
     * @throws UnAuthorizedException if the logged-in user is not the owner of the project
     */
    private static void checkOwnership(User loggedUser, Project project) {
        if (!loggedUser.getId().equals(project.getUser().getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }

    /**
     * Checks if the logged-in user is a member of the specified list of users.
     * If the logged-in user is not a member, an exception is thrown.
     *
     * @param users the list of users to check membership against
     * @param loggedUser the logged-in user
     * @throws UnAuthorizedException if the logged-in user is not a member of the list of users
     */
    private static void checkMembership(List<User> users, User loggedUser) {
        Set<Long> userIdSet = users.stream()
            .map(User::getId)
            .collect(Collectors.toSet());

        if (!userIdSet.contains(loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }
}
