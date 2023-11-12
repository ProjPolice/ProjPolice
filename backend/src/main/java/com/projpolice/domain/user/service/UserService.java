package com.projpolice.domain.user.service;

import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.domain.user.response.UserLogoutResponse;
import com.projpolice.global.common.base.BaseIdItem;

public interface UserService {
    BaseIdItem join(UserJoinRequest request);

    UserLoginResponse login(UserLoginRequest request);

    UserInfoResponse getUserInfo();

    UserInfoResponse updateUserInfo(UserUpdateRequest request);

    UserLogoutResponse logout();

}
