package com.projpolice.domain.task.service;

import org.springframework.stereotype.Service;

import com.projpolice.domain.epic.domain.Epic;
import com.projpolice.domain.epic.repository.EpicRepository;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.task.dto.TaskDetailItem;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.domain.task.request.TaskCreateRequest;
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

    /**
     * 상세 작업 생성
     * @param taskCreateRequest
     * @return 생성한 상세 작업 상세 내용
     */
    @Override
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
}
