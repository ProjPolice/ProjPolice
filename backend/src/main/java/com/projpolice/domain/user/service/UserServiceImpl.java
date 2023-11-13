package com.projpolice.domain.user.service;

import static com.projpolice.domain.user.service.JwtService.*;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.domain.redis.RefreshTokenRedisData;
import com.projpolice.domain.user.repository.rdb.UserRepository;
import com.projpolice.domain.user.repository.redis.RefreshTokenRepository;
import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.domain.user.response.UserLogoutResponse;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.exception.UnAuthorizedException;
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
    private final RefreshTokenRepository refreshTokenRepository;

    private final String CONTENT_TYPE = "multipart/form-data";

    /**
     * 사용자 등록을 한다.
     *
     * @return UserJoinResponse
     */
    @Override
    @Transactional
    public BaseIdItem join(UserJoinRequest request, MultipartFile image) {
        String imageUuid = null;

        if (image != null) {
            imageUuid = String.format("%s_%s", UUID.randomUUID(), image.getOriginalFilename());
            storageConnector.putObject(FileUtil.generateStreamFromFile(image), imageUuid, CONTENT_TYPE);
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
    public UserInfoResponse updateUserInfo(UserUpdateRequest request, MultipartFile image) {
        User user = getLoggedUser();

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (image != null) {
            String imageUuid = String.format("%s_%s", UUID.randomUUID(), image.getOriginalFilename());
            storageConnector.putObject(FileUtil.generateStreamFromFile(image), imageUuid, CONTENT_TYPE);
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
    @Transactional
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new BaseException(ExceptionInfo.LOGIN_FAIL));

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getId(),
                request.getPassword()
            )
        );

        user.setFcmToken(request.getFirebaseToken());
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return UserLoginResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }

    /**
     * 사용자 로그아웃하고 Redis에 있는 토큰 또한 삭제한다.
     *
     * @return UserLogoutResponse
     */
    @Transactional
    public UserLogoutResponse logout() {
        // User Entity could be at different entity, because it's in different Transaction
        User loggedUser = getLoggedUser();
        loggedUser.setFcmToken(null);
        // Check whether it works as 'dirty check update' or 'merge'
        userRepository.save(loggedUser);

        refreshTokenRepository.deleteById(String.valueOf(loggedUser.getId()));
        return UserLogoutResponse.builder()
            .id(loggedUser.getId())
            .build();
    }

    /**
     * RefreshToken을 입력받아서 만약 Redis에 같은 RefreshToken이 있을경우 재발급 해준다.
     *
     * @return UserLogoutResponse
     */
    public String reissue(String refreshToken) {
        String userId;
        try {
            userId = jwtService.extractUsername(refreshToken);
        } catch (UnAuthorizedException exception) {
            if (exception.getCode().equals(ExceptionInfo.ACCESS_TOKEN_EXPIRED.getCode())) {
                throw new UnAuthorizedException(ExceptionInfo.REFRESH_TOKEN_EXPIRED);
            }
            throw exception;
        }

        RefreshTokenRedisData refreshTokenRedisData = refreshTokenRepository.findById(userId)
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_REFRESH_TOKEN));

        User user = userRepository.findById(Long.valueOf(userId))
            .orElseThrow(() -> new BaseException(ExceptionInfo.INVALID_REFRESH_TOKEN));

        if (refreshToken.equals(refreshTokenRedisData.getRefreshToken())) {
            return jwtService.generateToken(user);
        } else {
            throw new BaseException(ExceptionInfo.INVALID_REFRESH_TOKEN);
        }
    }

}
