package com.projpolice.global.storage.base;

import java.io.InputStream;

import com.oracle.bmc.objectstorage.responses.DeleteObjectResponse;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import com.oracle.bmc.objectstorage.responses.PutObjectResponse;

public interface StorageConnector {
    // todo: file 연동 시 반환 타입을 file DTO로 수정 필요
    PutObjectResponse putObject(InputStream file, String objectName, String contentType);

    GetObjectResponse getObject(String objectName);

    DeleteObjectResponse deleteObject(String objectName);
}
