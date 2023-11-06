package com.projpolice.global.storage.oracle;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oracle.cloud")
public class OracleCloudObjectStorageConstantProvider {
    private final String namespace;
    private final String bucket;
    private final String privateKey;
    private final String ociApiConfigPath;
}
