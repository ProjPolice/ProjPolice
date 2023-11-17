package com.projpolice.domain.user.response;

import lombok.Builder;
import lombok.Data;

/**
 * This class represents the response object returned by the User Login API.
 * It contains the access token and refresh token of the authenticated user.
 */
@Data
@Builder
public class UserLoginResponse{
    private String accessToken;
    private String refreshToken;
}
