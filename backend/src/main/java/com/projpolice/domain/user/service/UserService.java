package com.projpolice.domain.user.service;

import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.response.UserJoinResponse;
import com.projpolice.domain.user.response.UserLoginResponse;

public interface UserService {
    UserJoinResponse join(UserJoinRequest request);
    UserLoginResponse login(UserLoginRequest request);
}
