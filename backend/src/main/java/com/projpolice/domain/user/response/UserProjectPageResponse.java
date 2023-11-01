package com.projpolice.domain.user.response;

import java.util.List;

import com.projpolice.domain.project.dto.ProjectIdNameDescData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProjectPageResponse {
    private List<ProjectIdNameDescData> projects;
}
