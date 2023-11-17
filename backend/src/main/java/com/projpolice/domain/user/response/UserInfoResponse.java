package com.projpolice.domain.user.response;

import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.global.storage.base.StorageProvider;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private long id;
    private String name;
    private String email;
    private String image;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .image(user.getImage() == null ? null :
                String.format("%s%s", StorageProvider.getPreAuthenticatedUrl(), user.getImage()))
            .build();
    }
}
