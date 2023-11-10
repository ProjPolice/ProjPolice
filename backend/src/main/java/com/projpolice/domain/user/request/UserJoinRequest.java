package com.projpolice.domain.user.request;

import com.projpolice.domain.user.domain.rdb.User;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a user join request.
 */
@Getter
@Setter
public class UserJoinRequest {
    private String name;
    private String email;
    private String password;

    public static User to(UserJoinRequest request, String imageUuid, String password) {
        return User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .image(imageUuid)
            .password(password)
            .build();
    }
}
