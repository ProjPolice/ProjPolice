package com.projpolice.domain.user.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private long id;
    private String name;
    private String email;
    private String image;
}
