package com.projpolice.global.storage.oracle;

import java.io.InputStream;
import java.util.Collection;

import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.oracle.bmc.objectstorage.model.StorageTier;
import com.oracle.bmc.objectstorage.requests.DeleteObjectRequest;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.DeleteObjectResponse;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import com.oracle.bmc.objectstorage.responses.PutObjectResponse;
import com.projpolice.global.common.error.exception.FileException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.util.FileUtil;
import com.projpolice.global.storage.base.StorageConnector;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OracleCloudObjectStorageConnector implements StorageConnector {
    private final OracleCloudObjectStorageConstantProvider constantProvider;
    private final ObjectStorageClient client;

    /**
     * Cloud Storage ObjectStorage에 Object 업로드
     *
     * @param file        InputStream
     * @param objectName  String
     * @param contentType String
     * @return Oracle Storage 오브젝트 결과
     * @see PutObjectRequest
     */
    public void putObject(InputStream file, String objectName, String contentType) {
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = PutObjectRequest.builder()
                .bucketName(constantProvider.getBucket())
                .namespaceName(constantProvider.getNamespace())
                .objectName(objectName)
                .contentType(contentType)
                .contentLength(30L)
                .putObjectBody(file)
                .storageTier(StorageTier.Standard).build();
        } catch (Exception e) {
            throw new FileException(ExceptionInfo.FAILED_FILE_UPLOAD);
        }

        if (putObjectRequest == null) {
            throw new FileException(ExceptionInfo.FAILED_FILE_UPLOAD);
        }

        PutObjectResponse response = client.getClient().putObject(putObjectRequest);
        if (response.getLastModified() == null) {
            throw new FileException(ExceptionInfo.FAILED_FILE_UPLOAD);
        }
    }

    /**
     * Cloud Storage ObjectStorage에 Object 가져오기
     *
     * @param objectName String
     * @return GetObjectResponse
     * @see GetObjectResponse
     */
    public Resource getObject(String objectName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucketName(constantProvider.getBucket())
            .namespaceName(constantProvider.getNamespace())
            .objectName(objectName).build();

        if (getObjectRequest == null) {
            throw new FileException(ExceptionInfo.FAILED_FILE_DOWNLOAD);
        }

        GetObjectResponse response = client.getClient().getObject(getObjectRequest);

        return FileUtil.toResource(response);
    }

    /**
     * Cloud Storage ObjectStorage에 Object 삭제하기
     *
     * @param objectName String
     * @return DeleteObjectResponse
     * @see DeleteObjectResponse
     */
    public void deleteObject(String objectName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucketName(constantProvider.getBucket())
            .namespaceName(constantProvider.getNamespace())
            .objectName(objectName)
            .build();

        if (deleteObjectRequest == null) {
            throw new FileException(ExceptionInfo.FAILED_FILE_DELETE);
        }

        client.getClient().deleteObject(deleteObjectRequest);
    }

    @Override
    public String getPreAuthenticatedUrl() {
        return constantProvider.getPreAuthenticatedUrl();
    }

    // use thread pool in future
    @Async
    public void deleteObjectByBatchAndIgnore(Collection<String> objectNames) {
        for (String objectName : objectNames) {
            try {
                deleteObject(objectName);
            } catch (Exception e) {
                log.error("[ObjectStorage batch deletion fail on {}] : {}", objectName, e.getMessage());
            }
        }
    }
}
