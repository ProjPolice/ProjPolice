package com.projpolice.domain.file.service;

import java.util.List;

import com.projpolice.domain.file.dto.FileBaseItem;
import com.projpolice.domain.file.dto.FileDetailItem;
import com.projpolice.domain.file.request.FileUploadRequest;

public interface FileService {
    FileDetailItem uploadFile(FileUploadRequest fileUploadRequest, long taskId);
    FileBaseItem deleteFile(long fileId);
    List<FileDetailItem> getTaskFile(long taskId);
}
