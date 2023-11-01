package com.projpolice.domain.project.service;

import static com.projpolice.global.common.error.info.ExceptionInfo.*;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
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
        TODO: \
         check whether the current user is owner or member of project \
          or the project is shared
         */
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

        // TODO: insert user when user context holder is ready
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Project project = Project.builder()
            .name(request.getName())
            .description(request.getDescription())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .user(currentUser)
            .build();

        projectRepository.save(project);

        return ProjectDetailData.from(project);
    }

    @Override
    public BaseIdItem deleteProject(long id) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );

        // TODO: insert user when user context holder is ready
        User currentUser = null;

        if (currentUser.getId() != project.getUser().getId()) {
            throw new UnAuthorizedException(UNAUTHORIZED);
        }

        BaseIdItem baseIdItem = new BaseIdItem(project.getId());
        projectRepository.delete(project);
        return baseIdItem;
    }

    @Override
    public ProjectDetailData modifyProject(long id, ProjectModifyRequest request) {
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionInfo.INVALID_PROJECT)
        );

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

        // TODO: 권한 체크

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
     * @param projectId the ID of the project
     * @param request the request object containing the email of the user to be added
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

        // TODO: 로그인한 유저가 프로젝트 주인인지 확인
        // if(project.getUser().equals(loggedUser)){
        //
        // }

        UserProject userProject = new UserProject(newUser, project);
        userProjectRepository.save(userProject);

        return UserIdNameImgItem.builder()
            .id(newUser.getId())
            .name(newUser.getName())
            .image(newUser.getImage())
            .build();
    }
}
