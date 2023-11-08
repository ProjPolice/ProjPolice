package com.projpolice.global.common.deletion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projpolice.domain.epic.repository.rdb.EpicRepository;
import com.projpolice.domain.file.repository.FileRepository;
import com.projpolice.domain.project.repository.rdb.ProjectRepository;
import com.projpolice.domain.task.dto.ProjectIdEpicIdProjectionData;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.redis.RedisService;
import com.projpolice.global.storage.base.StorageConnector;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletionServiceImpl implements DeletionService {
    private final ProjectRepository projectRepository;
    private final EpicRepository epicRepository;
    private final TaskRepository taskRepository;
    private final FileRepository fileRepository;
    private final StorageConnector storageConnector;
    private final RedisService redisService;

    @Override
    public void deleteTask(long taskId) {
        List<String> uuid = fileRepository.listUuidByTaskId(taskId);
        fileRepository.deleteAllByUuid(uuid);
        ProjectIdEpicIdProjectionData projection = taskRepository.findProjectIdEpicIdById(taskId).orElseThrow(
            () -> new BaseException(ExceptionInfo.INVALID_TASK)
        );
        redisService.invalidateEpic(projection.getEpicId(), projection.getProjectId());
        storageConnector.deleteObjectByBatchAndIgnore(uuid);
    }

    @Override
    public void deleteEpic(long epicId) {
        List<String> uuid = fileRepository.listUuidByEpicId(epicId);
        fileRepository.deleteAllByEpicId(epicId);
        taskRepository.deleteAllByEpicId(epicId);
        epicRepository.deleteById(epicId);
        long projectId = epicRepository.findProjectIdByEpicId(epicId).getAsLong();
        redisService.invalidateEpic(epicId, projectId);
        storageConnector.deleteObjectByBatchAndIgnore(uuid); // why last?->if transaction fail, object should be kept
    }

    @Override
    public void deleteProject(long projectId) {
        List<String> uuid = fileRepository.listUuidByProjectId(projectId);
        fileRepository.deleteAllByProjectId(projectId);
        taskRepository.deleteAllByProjectId(projectId);
        epicRepository.deleteAllByProjectId(projectId);
        projectRepository.deleteById(projectId);
        redisService.invalidateProject(projectId);
        storageConnector.deleteObjectByBatchAndIgnore(uuid); // why last?->if transaction fail, object should be kept
    }
}
