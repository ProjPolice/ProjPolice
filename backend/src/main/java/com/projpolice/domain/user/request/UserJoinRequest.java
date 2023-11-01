package com.projpolice.domain.user.request;

import lombok.Data;

/**
 * Represents a user join request.
 */
@Data
public class UserJoinRequest {
    private String name;
    private String email;
    private String image;
    private String password;
}
