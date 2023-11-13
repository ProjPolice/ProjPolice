package com.projpolice.global.notification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projpolice.domain.epic.domain.rdb.Epic;
import com.projpolice.domain.epic.repository.rdb.EpicRepository;
import com.projpolice.domain.project.dto.ProjectNameUserNameProjection;
import com.projpolice.domain.project.repository.rdb.ProjectRepository;
import com.projpolice.domain.task.dto.TaskChangePackagingDto;
import com.projpolice.domain.task.dto.TaskNameProjectNameOwnerNameProjectionData;
import com.projpolice.domain.task.repository.rdb.TaskRepository;
import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.repository.rdb.UserRepository;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.meta.domain.TaskStatus;
import com.projpolice.global.notification.firebase.FirebaseNotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final EpicRepository epicRepository;
    private final FirebaseNotificationService firebaseNotificationService;

    @Override
    public void userInvitedToProject(long projectId, long userId) {
        ProjectNameUserNameProjection projectProjection = projectRepository.findNameUserNameProjectionById(projectId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_PROJECT));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String fcmToken = user.getFcmToken();
        if (fcmToken == null) {
            return;
        }

        final String projectName = projectProjection.getProjectName();
        final String projectOwnerName = projectProjection.getUserName();

        final String title = String.format("새로운 프로젝트(%s)에 초대 되었습니다.", projectName);
        final String body = String.format("%s에 %s님에 의해 초대 되었습니다.", projectName, projectOwnerName);

        firebaseNotificationService.userInvitedToProject(projectId, title, body, fcmToken);
    }

    @Override
    public void userRemovedFromProject(long projectId, long userId) {
        ProjectNameUserNameProjection projectProjection = projectRepository.findNameUserNameProjectionById(projectId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_PROJECT));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String fcmToken = user.getFcmToken();
        if (fcmToken == null) {
            return;
        }

        final String projectName = projectProjection.getProjectName();
        final String projectOwnerName = projectProjection.getUserName();

        final String title = String.format("프로젝트(%s)에서 제외 되었습니다.", projectName);
        final String body = String.format("%s에서 %s님에 의해 제외 되셨습니다.", projectName, projectOwnerName);

        firebaseNotificationService.userRemovedFromProject(projectId, title, body, fcmToken);
    }

    @Override
    public void taskAssignedToUser(long taskId, long userId) {
        TaskNameProjectNameOwnerNameProjectionData task =
            taskRepository.findNameProjectNameProjectionById(taskId)
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_TASK));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String fcmToken = user.getFcmToken();
        if (fcmToken == null) {
            return;
        }

        final String projectName = task.getProjectName();
        final String taskName = task.getTaskName();
        final String projectOwner = task.getProjectOwnerName();

        final String title = String.format("새로운 작업(%s)이 할당 되었습니다.", taskName);
        final String body = String.format("%s에 새로운 작업 %s이 할당 되었습니다.", projectName, taskName);

        firebaseNotificationService.taskAssignedToUser(taskId, title, body, fcmToken);
    }

    @Override
    public void taskChanged(long taskId, long invokedUserId, TaskChangePackagingDto changes) {
        TaskNameProjectNameOwnerNameProjectionData task =
            taskRepository.findNameProjectNameProjectionById(taskId)
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_TASK));
        User invokedUser = userRepository.findById(invokedUserId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));

        final String projectName = task.getProjectName();
        final String title = String.format("%s 프로젝트 작업에 변동 사항이 존재합니다.", projectName);

        StringBuilder bodyBuilder = new StringBuilder();
        if (changes.getName() != null) {
            String[] names = changes.getName();
            bodyBuilder.append(String.format("이름이 %s에서 %s으로 바뀌었습니다.\n", names[0], names[1]));
        }

        if (changes.getDescription() != null) {
            String[] descriptions = changes.getDescription();
            bodyBuilder.append(String.format("내용이 %s에서 %s으로 바뀌었습니다.\n", descriptions[0], descriptions[1]));
        }

        if (changes.getEpicId() != null) {
            long[] epics = changes.getEpicId();
            // TODO: use projection
            Epic before = epicRepository.findById(epics[0])
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_EPIC));
            Epic after = epicRepository.findById(epics[1])
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_EPIC));
            String beforeName = before.getName();
            String afterName = after.getName();
            bodyBuilder.append(String.format("작업에 %s에 속했었으나 이제 %s에 속합니다.\n", beforeName, afterName));
        }

        if (changes.getUserId() != null) {
            long[] userId = changes.getUserId();
            User before = userRepository.findById(userId[0])
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));
            User after = userRepository.findById(userId[1])
                .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_USER));
            String beforeName = before.getName();
            String afterName = after.getName();
            bodyBuilder.append(String.format("작업의 담당자가 %s에서 %s으로 바뀌었습니다.\n", beforeName, afterName));
        }

        if (changes.getStartDate() != null) {
            LocalDate[] dates = changes.getStartDate();
            LocalDate before = dates[0];
            LocalDate after = dates[1];

            if (before != null && after != null) {
                bodyBuilder.append(String.format("작업의 시작일이 %s에서 %s으로 바뀌었습니다.\n", before.toString(), after.toString()));
            } else if (before == null) {
                bodyBuilder.append(String.format("작업의 시작일이 %s으로 설정되었습니다.\n", after.toString()));
            } else {
                bodyBuilder.append(String.format("작업의 시작일이 %s에서 해제되었습니다.\n", before.toString()));
            }
        }

        if (changes.getEndDate() != null) {
            LocalDate[] dates = changes.getEndDate();
            LocalDate before = dates[0];
            LocalDate after = dates[1];

            if (before != null && after != null) {
                bodyBuilder.append(String.format("작업의 마감일이 %s에서 %s으로 바뀌었습니다.\n", before.toString(), after.toString()));
            } else if (before == null) {
                bodyBuilder.append(String.format("작업의 마감일이 %s으로 설정되었습니다.\n", after.toString()));
            } else {
                bodyBuilder.append(String.format("작업의 마감일이 %s에서 해제되었습니다.\n", before.toString()));
            }
        }

        if (changes.getStatus() != null) {
            TaskStatus[] status = changes.getStatus();
            TaskStatus before = status[0];
            TaskStatus after = status[1];
            bodyBuilder.append(String.format("작업의 %s에서 %s으로 변경 되었습니다.", before.toString(), after.toString()));
        }

        final String body = bodyBuilder.toString();

        // TODO: change it as projection instead of using List<User>
        List<User> otherUsersRelatedToTask = taskRepository.findOtherUsersInTasksById(taskId, invokedUserId);
        List<String> emails = new ArrayList<>();
        List<String> fcmTokens = new ArrayList<>();
        for (User user : otherUsersRelatedToTask) {
            if (user.getFcmToken() != null) {
                fcmTokens.add(user.getFcmToken());
            }
            emails.add(user.getEmail());
        }

        if (!fcmTokens.isEmpty()) {
            firebaseNotificationService.taskChanged(taskId, title, body, fcmTokens);
        }
    }
}
