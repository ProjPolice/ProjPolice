package com.projpolice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.projpolice.global.storage.oracle.OracleCloudObjectStorageConstantProvider;

@SpringBootApplication
@EnableConfigurationProperties(OracleCloudObjectStorageConstantProvider.class)
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
