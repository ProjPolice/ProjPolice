package com.projpolice.global.storage.base;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class StorageProvider {
    private static String preAuthenticatedUrl;
    private StorageConnector storageConnector;

    StorageProvider(StorageConnector storageConnector) {
        this.storageConnector = storageConnector;
    }

    /**
     * Cloud Storage의 사전 검증된 Url 구하기
     * @return Cloud Storage 사전 검증된 url
     */
    public static String getPreAuthenticatedUrl() {
        return preAuthenticatedUrl;
    }

    @PostConstruct
    private void init() {
        this.preAuthenticatedUrl = this.storageConnector.getPreAuthenticatedUrl();
    }
}
