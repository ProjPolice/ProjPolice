package com.projpolice.domain.user.request;

import lombok.Data;
import lombok.Getter;

/**
 * Represents a user login request.
 */
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
}
