package com.projpolice.domain.user.response;

import com.projpolice.domain.user.domain.rdb.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private long id;
    private String name;
    private String email;
    private String image;

    public static UserInfoResponse of(User user, String preAuthenticatedUrl) {
        return UserInfoResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .image(user.getImage() == null ? null : String.format("%s%s", preAuthenticatedUrl, user.getImage()))
            .build();
    }
}
