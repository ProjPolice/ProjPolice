package com.projpolice.domain.file.service;

import java.util.List;

import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.request.FileUploadRequest;
import com.projpolice.global.common.base.BaseIdItem;

public interface FileService {
    FileDetailItem uploadFile(FileUploadRequest fileUploadRequest, long taskId);

    BaseIdItem deleteFile(long fileId);

    List<FileDetailItem> getTaskFileByTaskId(long taskId);

    List<FileDetailItem> getTaskFileByProjectId(long projectId);
}
