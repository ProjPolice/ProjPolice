package com.projpolice.domain.user.response;

import java.util.List;

import com.projpolice.domain.project.dto.ProjectIdNameDescData;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProjectPagingResponse {
    private List<ProjectIdNameDescData> projects;
    private int pages;
    private int numOfRows;
}
