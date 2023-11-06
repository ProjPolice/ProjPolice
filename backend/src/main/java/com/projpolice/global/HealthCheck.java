package com.projpolice.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class HealthCheck {
    private final String profile;

    @Autowired
    HealthCheck(Environment env) {
        String profile = env.getProperty("projpolice.server.type");
        if ("blue".equals(profile) || "green".equals(profile)) {
            this.profile = profile;
        } else {
            this.profile = "blue";
        }
    }

    @GetMapping
    public String health() {
        return profile;
    }
}
