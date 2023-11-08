package com.projpolice.global.storage.base;

import java.io.InputStream;
import java.util.Collection;

import org.springframework.core.io.Resource;

public interface StorageConnector {
    void putObject(InputStream file, String objectName, String contentType);

    Resource getObject(String objectName);

    void deleteObject(String objectName);

    String getPreAuthenticatedUrl();

    void deleteObjectByBatchAndIgnore(Collection<String> objectNames);
}
