package com.projpolice.domain.task.service;

import static com.projpolice.domain.user.service.JwtService.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projpolice.domain.epic.domain.rdb.Epic;
import com.projpolice.domain.epic.repository.rdb.EpicRepository;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.repository.FileRepository;
import com.projpolice.domain.task.domain.rdb.Task;
import com.projpolice.domain.task.dto.ProjectDetailProjection;
import com.projpolice.domain.task.dto.ProjectTaskDetails;
import com.projpolice.domain.task.dto.TaskChangePackagingDto;
import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.dto.TaskRelatedProjectionData;
import com.projpolice.domain.task.dto.UserTaskProjectionData;
import com.projpolice.domain.task.repository.rdb.TaskRepository;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.request.TaskUpdateRequest;
import com.projpolice.domain.task.response.TaskGetResponse;
import com.projpolice.domain.task.response.TaskUpdateResponse;
import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.repository.rdb.UserRepository;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.deletion.DeletionService;
import com.projpolice.global.common.error.exception.EpicException;
import com.projpolice.global.common.error.exception.TaskException;
import com.projpolice.global.common.error.exception.UserException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;
import com.projpolice.global.notification.NotificationService;
import com.projpolice.global.redis.RedisService;
import com.projpolice.global.storage.base.StorageConnector;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final EpicRepository epicRepository;
    private final TaskRepository taskRepository;
    private final ProjectAuthManager projectAuthManager;
    private final FileRepository fileRepository;
    private final DeletionService deletionService;
    private final RedisService redisService;
    private final StorageConnector storageConnector;
    private final NotificationService notificationService;

    /**
     * 상세 작업 생성
     *
     * @param taskCreateRequest
     * @return 생성한 상세 작업 상세 내용
     */
    @Override
    @Transactional
    public TaskDetailItem createTask(TaskCreateRequest taskCreateRequest) {
        projectAuthManager.checkEpicMembershipOrThrow(taskCreateRequest.getEpicId());
        projectAuthManager.checkEpicMembershipWithUserIdOrThrow(taskCreateRequest.getEpicId(),
            taskCreateRequest.getUserId());

        User user = userRepository.findById(taskCreateRequest.getUserId())
            .orElseThrow(() -> new UserException(ExceptionInfo.INVALID_USER));
        Epic epic = epicRepository.findById(taskCreateRequest.getEpicId())
            .orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC));

        Task task = taskRepository.save(Task.of(taskCreateRequest, user, epic));

        if (!taskCreateRequest.getUserId().equals(getLoggedUser().getId())) {
            notificationService.taskAssignedToUser(task.getId(), taskCreateRequest.getUserId());
        }
        long projectId = epicRepository.findProjectIdByEpicId(taskCreateRequest.getEpicId())
            .orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC));

        redisService.invalidateProject(projectId);
        return TaskDetailItem.of(task, storageConnector.getPreAuthenticatedUrl());
    }

    /**
     * 세부 작업 수정 기능
     *
     * @param taskId
     * @param taskUpdateRequest
     * @return 수정한 세부 작업 값
     */
    @Override
    @Transactional
    public TaskUpdateResponse updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest) {
        projectAuthManager.checkTaskOwnershipOrThrow(taskId);
        TaskChangePackagingDto.Builder changeBuilder = TaskChangePackagingDto.builder();

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));
        boolean updated = false;

        if (taskUpdateRequest.getName() != null) {
            changeBuilder.name(task.getName(), taskUpdateRequest.getName());
            task.setName(taskUpdateRequest.getName());
            updated = true;
        }
        if (taskUpdateRequest.getDescription() != null) {
            changeBuilder.description(task.getDescription(), taskUpdateRequest.getDescription());
            task.setDescription(taskUpdateRequest.getDescription());
            updated = true;
        }
        if (taskUpdateRequest.getStartDate() != null) {
            changeBuilder.startDate(task.getStartDate(), taskUpdateRequest.getStartDate());
            task.setStartDate(taskUpdateRequest.getStartDate());
            updated = true;
        }
        if (taskUpdateRequest.getEndDate() != null) {
            changeBuilder.endDate(task.getEndDate(), taskUpdateRequest.getEndDate());
            task.setEndDate(taskUpdateRequest.getEndDate());
            updated = true;
        }
        if (taskUpdateRequest.getStatus() != null) {
            changeBuilder.status(task.getStatus(), taskUpdateRequest.getStatus());
            task.setStatus(taskUpdateRequest.getStatus());
            updated = true;
        }
        if (taskUpdateRequest.getUserId() != null) {
            changeBuilder.userId(task.getUser().getId(), taskUpdateRequest.getUserId());
            task.setUser(userRepository.findById(taskUpdateRequest.getUserId())
                .orElseThrow(() -> new UserException(ExceptionInfo.INVALID_USER)));
            updated = true;
        }
        if (taskUpdateRequest.getEpicId() != null) {
            changeBuilder.epicId(task.getEpic().getId(), taskUpdateRequest.getEpicId());
            task.setEpic(epicRepository.findById(taskUpdateRequest.getEpicId())
                .orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC)));
            updated = true;
        }

        if (updated) {
            notificationService.taskChanged(taskId, task.getUser().getId(), changeBuilder.build());
            long projectId = taskRepository.findProjectIdById(taskId)
                .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_TASK));
            redisService.invalidateProject(projectId);
        }

        return TaskUpdateResponse.of(task, storageConnector.getPreAuthenticatedUrl());
    }

    /**
     * 세부 작업 삭제 기능
     *
     * @param taskId
     * @return 삭제된 세부 작업의 Id
     */
    @Override
    @Transactional
    public BaseIdItem deleteTask(Long taskId) {
        projectAuthManager.checkTaskOwnershipOrThrow(taskId);
        deletionService.deleteTask(taskId);

        // Task가 존재하지 않으면 이미 Ownership 확인에서 Exception이 나와서 추가로 확인할 필요 X
        //Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));
        return new BaseIdItem(taskId);
    }

    /**
     * 세부 작업 조회
     *
     * @param taskId
     * @return 세부 작업 값
     */
    @Override
    @Transactional
    public TaskGetResponse getTask(Long taskId) {
        projectAuthManager.checkTaskMembershipOrThrow(taskId);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));
        return TaskGetResponse.of(task, fileRepository.findByTaskId(taskId).stream()
            .map(FileDetailItem::from)
            .collect(Collectors.toList()), storageConnector.getPreAuthenticatedUrl());
    }

    @Override
    public List<TaskRelatedProjectionData> selectUserTaskRelatedDataWithRange(LocalDate startDate,
        LocalDate endDate) {
        long userId = getLoggedUser().getId();
        Optional<List<TaskRelatedProjectionData>> cache = redisService.selectUserProjectsWithDateRange(
            userId, startDate, endDate);
        if (cache.isPresent()) {
            return cache.get();
        }

        List<UserTaskProjectionData> projections = taskRepository.findTasksByUserId(userId, startDate, endDate);
        List<TaskRelatedProjectionData> result = projections.stream()
            .map(TaskRelatedProjectionData::new)
            .toList();
        redisService.saveUserProjectsWithDateRange(userId, startDate, endDate, result);
        return result;
    }

    @Override
    public List<ProjectTaskDetails> selectProjectTaskDetailByProjectId(long projectId) {
        // add check

        List<ProjectDetailProjection> projectTaskDetailsByProjectId = taskRepository.findProjectTaskDetailsByProjectId(
            projectId);
        return projectTaskDetailsByProjectId.stream()
            .map(ProjectTaskDetails::new)
            .collect(Collectors.toList());
    }
}
