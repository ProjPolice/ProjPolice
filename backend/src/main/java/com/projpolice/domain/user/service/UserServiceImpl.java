package com.projpolice.domain.user.service;

import static com.projpolice.domain.user.service.JwtService.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projpolice.domain.user.domain.User;
import com.projpolice.domain.user.repository.UserRepository;
import com.projpolice.domain.user.request.UserJoinRequest;
import com.projpolice.domain.user.request.UserLoginRequest;
import com.projpolice.domain.user.request.UserUpdateRequest;
import com.projpolice.domain.user.response.UserInfoResponse;
import com.projpolice.domain.user.response.UserJoinResponse;
import com.projpolice.domain.user.response.UserLoginResponse;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * 사용자 등록을 한다.
     *
     * @return UserJoinResponse
     */
    public UserJoinResponse join(UserJoinRequest request) {
        User user = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .image(request.getImage())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

        userRepository.save(user);

        return UserJoinResponse.builder()
            .id(user.getId())
            .build();
    }

    /**
     * 인증된 사용자의 정보를 가져온다.
     *
     * @return UserInfoResponse
     */
    public UserInfoResponse getUserInfo() {
        User user = getLoggedUser();

        return UserInfoResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .image(user.getImage())
            .build();
    }

    /**
     * 인증된 사용자의 정보를 가져온다.
     *
     * @return UserInfoResponse
     */
    public UserInfoResponse updateUserInfo(UserUpdateRequest request) {
        User user = getLoggedUser();

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getImage() != null) {
            user.setImage(request.getImage());
        }

        userRepository.save(user);

        return UserInfoResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .image(user.getImage())
            .build();
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
