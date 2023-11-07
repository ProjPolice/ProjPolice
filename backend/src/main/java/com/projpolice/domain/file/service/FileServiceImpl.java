package com.projpolice.domain.file.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projpolice.domain.file.domain.File;
import com.projpolice.domain.file.dto.FileBaseItem;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.repository.FileRepository;
import com.projpolice.domain.file.request.FileUploadRequest;
import com.projpolice.domain.task.domain.Task;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.global.common.error.exception.TaskException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;
    private final ProjectAuthManager projectAuthManager;

    /**
     * 세부작업의 파일들을 조회하는 메소드
     *
     * @param taskId
     * @return EpicDetailData
     */
    @Override
    @Transactional
    public List<FileDetailItem> getTaskFile(long taskId) {

        return fileRepository.findByTaskId(taskId).stream()
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
        projectAuthManager.checkTaskMembershipOrThrow(taskId);

        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_TASK));

        File file = fileRepository.save(File.of(fileUploadRequest, task));
        return FileDetailItem.from(file);
    }

    /**
     * 파일을 삭제하는 메소드
     *
     * @param fileId
     * @return FileBaseItem
     */
    public FileBaseItem deleteFile(long fileId) {
        File file = fileRepository.findById(fileId)
            .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_FILE));

        projectAuthManager.checkTaskMembershipOrThrow(file.getTask().getId());

        fileRepository.delete(file);
        return FileBaseItem.builder()
            .id(fileId)
            .build();
    }
}
