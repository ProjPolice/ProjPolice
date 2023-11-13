package com.projpolice.domain.user.request;

import lombok.Getter;

/**
 * Represents a user login request.
 */
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
    private String firebaseToken; // FCM 토큰을 로그인할 때 받아온다.
}
