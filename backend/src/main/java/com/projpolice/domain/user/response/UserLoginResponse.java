package com.projpolice.domain.user.response;

import com.projpolice.domain.user.dto.UserIdNameImgItem;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * This class represents the response object returned by the User Login API.
 * It contains the access token and refresh token of the authenticated user.
 */
@Data
@SuperBuilder
public class UserLoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserIdNameImgItem user;
}
