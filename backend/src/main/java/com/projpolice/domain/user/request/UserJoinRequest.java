package com.projpolice.domain.user.request;

import lombok.Data;
import lombok.Getter;

/**
 * Represents a user join request.
 */
@Getter
public class UserJoinRequest {
    private String name;
    private String email;
    private String image;
    private String password;
}
