package com.projpolice.domain.file.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projpolice.domain.file.domain.File;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.repository.FileRepository;
import com.projpolice.domain.file.request.FileUploadRequest;
import com.projpolice.domain.task.domain.rdb.Task;
import com.projpolice.domain.task.repository.rdb.TaskRepository;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.TaskException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;
import com.projpolice.global.common.util.FileUtil;
import com.projpolice.global.storage.base.StorageConnector;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;
    private final ProjectAuthManager projectAuthManager;
    private final StorageConnector storageConnector;
    private final String CONTENT_TYPE = "multipart/form-data";

    /**
     * 세부작업의 파일들을 조회하는 메소드
     *
     * @param taskId
     * @return EpicDetailData
     */
    @Override
    @Transactional
    public List<FileDetailItem> getTaskFileByTaskId(long taskId) {
        projectAuthManager.checkTaskMembershipOrThrow(taskId);
        return fileRepository.findByTaskId(taskId).stream()
            .map(FileDetailItem::from)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<FileDetailItem> getTaskFileByProjectId(long projectId) {
        projectAuthManager.checkProjectMembershipOrThrow(projectId);
        return fileRepository.findByProjectId(projectId).stream()
            .map(FileDetailItem::from)
            .collect(Collectors.toList());
    }

    /**
     * 세부작업에 파일을 생성하는 메소드
     *
     * @param fileUploadRequest
     * @return EpicDetailData
     */
    @Override
    @Transactional
    public FileDetailItem uploadFile(FileUploadRequest fileUploadRequest, long taskId) {
        projectAuthManager.checkTaskOwnershipOrThrow(taskId);

        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_TASK));

        final String uuid = UUID.randomUUID().toString();

        // oracle cloud object storage에 파일 업로드
        storageConnector.putObject(FileUtil.generateStreamFromFile(fileUploadRequest.getFile()), uuid, CONTENT_TYPE);

        File file = fileRepository.save(File.of(fileUploadRequest, task, uuid));
        return FileDetailItem.from(file);
    }

    /**
     * 파일을 삭제하는 메소드
     *
     * @param fileId
     * @return FileBaseItem
     */
    @Override
    @Transactional
    public BaseIdItem deleteFile(long fileId) {
        long userId = fileRepository.findUserIdById(fileId)
            .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_FILE));
        projectAuthManager.checkUserIdMatchOrThrow(userId);
        fileRepository.deleteById(fileId);
        return new BaseIdItem(fileId);
    }
}
