package com.projpolice.api.request;

import lombok.Data;

/**
 * Represents a user login request.
 */
@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
