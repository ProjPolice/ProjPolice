package com.projpolice.domain.task.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projpolice.domain.epic.domain.rdb.Epic;
import com.projpolice.domain.epic.repository.rdb.EpicRepository;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.response.TaskGetResponse;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.domain.task.request.TaskCreateRequest;
import com.projpolice.domain.task.request.TaskUpdateRequest;
import com.projpolice.domain.task.response.TaskDeleteResponse;
import com.projpolice.domain.task.response.TaskUpdateResponse;
import com.projpolice.domain.user.domain.User;
import com.projpolice.domain.user.repository.UserRepository;
import com.projpolice.global.common.error.exception.EpicException;
import com.projpolice.global.common.error.exception.UserException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final EpicRepository epicRepository;
    private final TaskRepository taskRepository;
    private final ProjectAuthManager projectAuthManager;
    private final FileRepository fileRepository;

    /**
     * 상세 작업 생성
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

        return TaskDetailItem.from(task);
    }

    /**
     * 세부 작업 수정 기능
     * @param taskId
     * @param taskUpdateRequest
     * @return 수정한 세부 작업 값
     */
    @Override
    @Transactional
    public TaskUpdateResponse updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest) {
        projectAuthManager.checkTaskOwnershipOrThrow(taskId);

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));

        if (taskUpdateRequest.getName() != null) {
            task.setName(taskUpdateRequest.getName());
        }
        if (taskUpdateRequest.getDescription() != null) {
            task.setDescription(taskUpdateRequest.getDescription());
        }
        if (taskUpdateRequest.getStartDate() != null) {
            task.setStartDate(taskUpdateRequest.getStartDate());
        }
        if (taskUpdateRequest.getEndDate() != null) {
            task.setEndDate(taskUpdateRequest.getEndDate());
        }
        if (taskUpdateRequest.getStatus() != null) {
            task.setStatus(taskUpdateRequest.getStatus());
        }
        if (taskUpdateRequest.getUserId() != null) {
            task.setUser(userRepository.findById(taskUpdateRequest.getUserId())
                .orElseThrow(() -> new UserException(ExceptionInfo.INVALID_USER)));
        }
        if (taskUpdateRequest.getEpicId() != null) {
            task.setEpic(epicRepository.findById(taskUpdateRequest.getEpicId())
                .orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC)));
        }

        return TaskUpdateResponse.from(task);
    }

    /**
     * 세부 작업 삭제 기능
     * @param taskId
     * @return 삭제된 세부 작업의 Id
     */
    @Override
    @Transactional
    public TaskDeleteResponse deleteTask(Long taskId) {
        projectAuthManager.checkTaskOwnershipOrThrow(taskId);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_TASK));
        return TaskDeleteResponse.from(task);
    }

    /**
     * 세부 작업 조회
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
}
