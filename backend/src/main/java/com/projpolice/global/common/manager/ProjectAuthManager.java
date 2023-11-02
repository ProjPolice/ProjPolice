package com.projpolice.global.common.manager;

import static com.projpolice.domain.user.service.JwtService.*;
import static com.projpolice.global.common.error.info.ExceptionInfo.*;

import org.springframework.stereotype.Component;

import com.projpolice.domain.epic.repository.EpicRepository;
import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.project.repository.UserProjectRepository;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.domain.user.domain.User;
import com.projpolice.global.common.error.exception.UnAuthorizedException;

import lombok.RequiredArgsConstructor;

/**
 * The ProjectAuthManager class provides methods for checking the authorization
 * of the logged user for various operations related to projects, epics, and tasks.
 */
@Component
@RequiredArgsConstructor
public class ProjectAuthManager {

    private final ProjectRepository projectRepository;

    private final UserProjectRepository userProjectRepository;

    private final EpicRepository epicRepository;

    private final TaskRepository taskRepository;

    /**
     * Checks if the logged user is the owner of the specified project.
     *
     * @param projectId The ID of the project to be checked. (non-negative long value)
     * @throws UnAuthorizedException if the logged user is not the owner of the project.
     */
    public void checkProjectOwnershipOrThrow(long projectId) {
        User loggedUser = getLoggedUser();
        if (!projectRepository.checkOwnership(projectId, loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }

    /**
     * Checks if the logged user is the owner of the specified project.
     *
     * @param project The project to be checked.
     * @throws UnAuthorizedException if the logged user is not the owner of the project.
     */
    public void checkProjectOwnershipOrThrow(Project project) {
        User loggedUser = getLoggedUser();
        if (!project.getUser().getId().equals(loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }

    /**
     * Checks if the logged user is a member of the specified project.
     *
     * @param projectId The ID of the project to be checked.
     * @throws UnAuthorizedException if the logged user is not a member of the project.
     */
    public void checkProjectMembershipOrThrow(long projectId) {
        User loggedUser = getLoggedUser();
        if (!userProjectRepository.checkMembership(projectId, loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }

    /**
     * Checks if the logged user is a member of the specified epic.
     *
     * @param epicId The ID of the epic to be checked.
     * @throws UnAuthorizedException if the logged user is not a member of the epic.
     */
    public void checkEpicMembershipOrThrow(long epicId) {
        User loggedUser = getLoggedUser();
        if (!epicRepository.checkMembership(epicId, loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }

    /**
     * Checks if the logged user is the owner of the specified task.
     *
     * @param taskId The ID of the task to be checked.
     * @throws UnAuthorizedException if the logged user is not the owner of the task.
     */
    public void checkTaskOwnershipOrThrow(long taskId) {
        User loggedUser = getLoggedUser();
        if (!taskRepository.checkOwnership(taskId, loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }

    /**
     * Checks if the logged user is a member of the specified task.
     *
     * @param taskId The ID of the task to be checked.
     * @throws UnAuthorizedException if the logged user is not a member of the task.
     */
    public void checkTaskMembershipOrThrow(long taskId) {
        User loggedUser = getLoggedUser();
        if (!taskRepository.checkMembership(taskId, loggedUser.getId())) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }
    }
}
