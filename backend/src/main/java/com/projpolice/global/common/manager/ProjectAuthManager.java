package com.projpolice.global.common.manager;

import static com.projpolice.domain.user.service.JwtService.*;

import org.springframework.stereotype.Component;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.project.repository.UserProjectRepository;
import com.projpolice.domain.user.domain.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProjectAuthManager {

    private final ProjectRepository projectRepository;

    private final UserProjectRepository userProjectRepository;

    /**
     * Checks if the logged user is the owner of the specified project.
     *
     * @param projectId The ID of the project to be checked.
     * @return true if the logged user is the owner of the project, false otherwise.
     */
    public boolean checkProjectOwnership(long projectId) {
        User loggedUser = getLoggedUser();
        return projectRepository.checkOwnership(projectId, loggedUser.getId());
    }

    /**
     * Checks if the logged user is the owner of the specified project.
     *
     * @param project The project to be checked.
     * @return true if the logged user is the owner of the project, false otherwise.
     */
    public boolean checkProjectOwnership(Project project) {
        User loggedUser = getLoggedUser();
        return project.getUser().getId().equals(loggedUser.getId());
    }

    /**
     * Checks if the logged user is a member of the specified project.
     *
     * @param projectId The ID of the project to be checked.
     * @return true if the logged user is a member of the project, false otherwise.
     */
    public boolean checkProjectMembership(long projectId) {
        User loggedUser = getLoggedUser();
        return userProjectRepository.checkMembership(projectId, loggedUser.getId());
    }
}
