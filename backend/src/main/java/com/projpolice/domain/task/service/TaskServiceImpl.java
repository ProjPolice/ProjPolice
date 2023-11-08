package com.projpolice.domain.task.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projpolice.domain.epic.domain.rdb.Epic;
import com.projpolice.domain.epic.repository.rdb.EpicRepository;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.repository.FileRepository;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.dto.TaskRelatedProjectionData;
import com.projpolice.domain.task.dto.UserTaskProjectionData;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.request.TaskUpdateRequest;
import com.projpolice.domain.task.response.TaskGetResponse;
import com.projpolice.domain.task.response.TaskUpdateResponse;
import com.projpolice.domain.user.domain.User;
import com.projpolice.domain.user.repository.UserRepository;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.deletion.DeletionService;
import com.projpolice.global.common.error.exception.EpicException;
import com.projpolice.global.common.error.exception.UserException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;
import com.projpolice.global.redis.RedisService;

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
        long projectId = epicRepository.findProjectIdByEpicId(taskCreateRequest.getEpicId()).getAsLong();
        redisService.invalidateEpic(taskCreateRequest.getEpicId(), projectId);
        return TaskDetailItem.from(task);
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

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));
        boolean updated = false;

        if (taskUpdateRequest.getName() != null) {
            task.setName(taskUpdateRequest.getName());
            updated = true;
        }
        if (taskUpdateRequest.getDescription() != null) {
            task.setDescription(taskUpdateRequest.getDescription());
            updated = true;
        }
        if (taskUpdateRequest.getStartDate() != null) {
            task.setStartDate(taskUpdateRequest.getStartDate());
            updated = true;
        }
        if (taskUpdateRequest.getEndDate() != null) {
            task.setEndDate(taskUpdateRequest.getEndDate());
            updated = true;
        }
        if (taskUpdateRequest.getStatus() != null) {
            task.setStatus(taskUpdateRequest.getStatus());
            updated = true;
        }
        if (taskUpdateRequest.getUserId() != null) {
            task.setUser(userRepository.findById(taskUpdateRequest.getUserId())
                .orElseThrow(() -> new UserException(ExceptionInfo.INVALID_USER)));
            updated = true;
        }
        if (taskUpdateRequest.getEpicId() != null) {
            task.setEpic(epicRepository.findById(taskUpdateRequest.getEpicId())
                .orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC)));
            updated = true;
        }

        if (updated) {
            long projectId = taskRepository.findProjectIdById(taskId).getAsLong();
            redisService.invalidateEpic(taskUpdateRequest.getEpicId(), projectId);
        }

        return TaskUpdateResponse.from(task);
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
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));
        TaskGetResponse taskItem = TaskGetResponse.from(task, fileRepository.findByTaskId(taskId).stream()
            .map(FileDetailItem::from)
            .collect(Collectors.toList()));
        return taskItem;
    }

    @Override
    public List<TaskRelatedProjectionData> selectUserTaskRelatedDataWithRange(long userId, LocalDate startDate,
        LocalDate endDate) {

        List<UserTaskProjectionData> projections = taskRepository.findTasksByUserId(userId, startDate, endDate);
        List<TaskRelatedProjectionData> result = projections.stream()
            .map(TaskRelatedProjectionData::new)
            .toList();

        return result;
    }
}
