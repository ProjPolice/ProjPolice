package com.projpolice.global.notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.projpolice.domain.project.dto.ProjectNameUserNameProjection;
import com.projpolice.domain.project.repository.rdb.ProjectRepository;
import com.projpolice.domain.task.dto.TaskNameProjectNameOwnerNameProjectionData;
import com.projpolice.domain.task.repository.rdb.TaskRepository;
import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.repository.rdb.UserRepository;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.notification.dto.NotificationModalData;
import com.projpolice.global.notification.dto.NotificationOption;
import com.projpolice.global.notification.dto.NotificationWebPush;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${projpolice.icon.path}")
    private String iconPath;

    @Value("${projpolice.base.url}")
    private String baseUrl;

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public void userInvitedToProject(long projectId, long userId) {
        ProjectNameUserNameProjection projectProjection = projectRepository.findNameUserNameProjectionById(projectId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_PROJECT));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String projectName = projectProjection.getProjectName();
        final String projectOwnerName = projectProjection.getUserName();
        final String url = String.format("%s/", baseUrl);

        NotificationWebPush push = NotificationWebPush.builder()
            .notification(
                NotificationModalData.builder()
                    .title("새로운 프로젝트에 초대 되었습니다.")
                    .body(String.format("%s에 %s님에 의해 초대 되었습니다.", projectName, projectOwnerName))
                    .build()
            )
            .fcm_options(
                NotificationOption.builder()
                    .link(url)
                    .analytics_label(String.format("project_%d", projectId))
                    .build()
            )
            .build();

        // user -> token 가지고 와서 push 발송
    }

    @Override
    public void userRemovedFromProject(long projectId, long userId) {
        ProjectNameUserNameProjection projectProjection = projectRepository.findNameUserNameProjectionById(projectId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_PROJECT));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String projectName = projectProjection.getProjectName();
        final String projectOwnerName = projectProjection.getUserName();
        final String url = String.format("%s/", baseUrl);

        NotificationWebPush push = NotificationWebPush.builder()
            .notification(
                NotificationModalData.builder()
                    .title("프로젝트에서 제외 되었습니다.")
                    .body(String.format("%s에서 %s님에 의해 제외 되셨습니다.", projectName, projectOwnerName))
                    .build()
            )
            .fcm_options(
                NotificationOption.builder()
                    .link(url)
                    .analytics_label(String.format("project_%d", projectId))
                    .build()
            )
            .build();

        // user -> token 가지고 와서 push 발송
    }

    @Override
    public void taskAssignedToUser(long taskId, long userId) {
        TaskNameProjectNameOwnerNameProjectionData task =
            taskRepository.findNameProjectNameProjectionById(taskId)
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_TASK));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String projectName = task.getProjectName();
        final String taskName = task.getTaskName();
        final String projectOwner = task.getProjectOwnerName();
        final String url = String.format("%s/", baseUrl);

        NotificationWebPush push = NotificationWebPush.builder()
            .notification(
                NotificationModalData.builder()
                    .title("새로운 작업이 할당 되었습니다.")
                    .body(String.format("%s에 새로운 작업 %s이 할당 되었습니다.", projectName, taskName))
                    .build()
            )
            .fcm_options(
                NotificationOption.builder()
                    .link(url)
                    .analytics_label(String.format("task_%d", taskId))
                    .build()
            )
            .build();

        // user -> token 가지고 와서 push 발송
    }

    @Override
    public void taskChanged(long taskId) {

    }
}
