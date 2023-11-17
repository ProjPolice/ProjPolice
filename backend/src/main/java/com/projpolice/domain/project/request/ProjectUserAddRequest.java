package com.projpolice.domain.project.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProjectUserAddRequest {
    @NotNull
    private String memberEmail;
}
