package com.projpolice.global.storage.oracle;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oracle.bmc.Region;
import com.oracle.bmc.http.ResteasyClientConfigurator;
import com.oracle.bmc.objectstorage.ObjectStorage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Getter
@NoArgsConstructor
public class ObjectStorageClient {
    private ObjectStorage client;
    private OracleCloudObjectStorageConstantProvider constantProvider;

    @Autowired
    ObjectStorageClient(OracleCloudObjectStorageConstantProvider constantProvider) throws IOException {
        this.constantProvider = constantProvider;
        // ObjectStorage의 Client 생성
        this.client = com.oracle.bmc.objectstorage.ObjectStorageClient.builder()
            .additionalClientConfigurator(new ResteasyClientConfigurator())
            .build((new OracleCloudAuthentificationProvider()).getAuthenticationDetailsProvider(
                constantProvider.getPrivateKey(),
                constantProvider.getOciApiConfigPath()));
        this.client.setRegion(Region.AP_CHUNCHEON_1);
    }
}
