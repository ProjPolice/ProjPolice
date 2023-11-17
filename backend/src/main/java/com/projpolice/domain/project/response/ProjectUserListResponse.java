package com.projpolice.domain.project.response;

import java.util.List;

import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserListResponse {
    private List<UserIdNameImgItem> members;
}
