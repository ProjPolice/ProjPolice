package com.projpolice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import com.projpolice.global.storage.oracle.OracleCloudObjectStorageConstantProvider;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(OracleCloudObjectStorageConstantProvider.class)
@OpenAPIDefinition(servers = {@Server(url = "https://api.projpolice.com/", description = "프로폴리스 API Server"),
    @Server(url = "http://localhost:8080/", description = "로컬 API 기본 서버"),
    @Server(url = "http://localhost:8081/", description = "로컬 API 블루 서버"),
    @Server(url = "http://localhost:8082/", description = "로컬 API 그린 서버")
})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
