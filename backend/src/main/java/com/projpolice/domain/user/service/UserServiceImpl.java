package com.projpolice.domain.user.service;

import static com.projpolice.domain.user.service.JwtService.*;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.repository.rdb.UserRepository;
import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.util.FileUtil;
import com.projpolice.global.storage.base.StorageConnector;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StorageConnector storageConnector;

    private final String CONTENT_TYPE = "multipart/form-data";

    /**
     * 사용자 등록을 한다.
     *
     * @return UserJoinResponse
     */
    @Override
    @Transactional
    public BaseIdItem join(UserJoinRequest request) {
        String imageUuid = null;

        if (request.getImage() != null) {
            imageUuid = String.format("%s_%s", UUID.randomUUID(), request.getImage().getOriginalFilename());
            storageConnector.putObject(FileUtil.generateStreamFromFile(request.getImage()), imageUuid, CONTENT_TYPE);
        }

        User user = userRepository.save(
            UserJoinRequest.to(request, imageUuid, passwordEncoder.encode(request.getPassword())));

        return BaseIdItem.from(user.getId());
    }

    /**
     * 인증된 사용자의 정보를 가져온다.
     *
     * @return UserInfoResponse
     */
    @Override
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo() {
        User user = getLoggedUser();

        return UserInfoResponse.of(user, storageConnector.getPreAuthenticatedUrl());
    }

    /**
     * 인증된 사용자의 정보를 가져온다.
     *
     * @return UserInfoResponse
     */
    @Override
    @Transactional
    public UserInfoResponse updateUserInfo(UserUpdateRequest request) {
        User user = getLoggedUser();

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getImage() != null) {
            String imageUuid = String.format("%s_%s", UUID.randomUUID(), request.getImage().getOriginalFilename());
            storageConnector.putObject(FileUtil.generateStreamFromFile(request.getImage()), imageUuid, CONTENT_TYPE);
            user.setImage(imageUuid);
        }

        userRepository.save(user);

        return UserInfoResponse.of(user, storageConnector.getPreAuthenticatedUrl());
    }

    /**
     * 사용자 정보의 유효성을 점검하고 인증을 한다.
     *
     * @return UserLoginResponse
     */
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new BaseException(ExceptionInfo.LOGIN_FAIL));

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getId(),
                request.getPassword()
            )
        );

        String jwtToken = jwtService.generateToken(user);
        return UserLoginResponse.builder()
            .accessToken(jwtToken)
            .build();
    }

}
