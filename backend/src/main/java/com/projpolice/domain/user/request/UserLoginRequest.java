package com.projpolice.domain.user.request;

import lombok.Data;

/**
 * Represents a user login request.
 */
@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
