package com.projpolice.domain.user.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.projpolice.domain.user.domain.rdb.User;
import com.projpolice.domain.user.domain.redis.RefreshTokenRedisData;
import com.projpolice.domain.user.repository.redis.RefreshTokenRepository;
import com.projpolice.global.common.error.exception.UnAuthorizedException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Spring Security의 Jwt 와 관련된 작업들에 대한 컴포넌트이다.
 */
@Service
public class JwtService {

    private final String SECRET_KEY;
    private final RefreshTokenRepository refreshTokenRepository;

    private final int accessTokenExpireMinutes;

    private final int refreshTokenExpireMinutes;

    @Autowired
    public JwtService(Environment env, RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        SECRET_KEY = env.getProperty("JWT_SECRET_KEY");
        accessTokenExpireMinutes =
            Integer.parseInt(env.getProperty("projpolice.jwt.access.minutes", "24")) * 6000; // 24분
        refreshTokenExpireMinutes =
            Integer.parseInt(env.getProperty("projpolice.jwt.refresh.minutes", "2880")) * 6000; // 2일
    }

    /**
     * Jwt 토큰으로부터 사용자 메일을 추출한다.
     *
     * @return String token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Jwt 토큰으로부터 원하는 Claim을 추출한다.
     *
     * @return claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException(ExceptionInfo.ACCESS_TOKEN_EXPIRED);
        }
    }

    /**
     * UserDetail객체에 해당하는 Jwt를 생성한다.
     *
     * @return String token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, String userId) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(String.valueOf(userId))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireMinutes))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String generateToken(Map<String, Object> extraClaims, long userId) {
        return generateToken(extraClaims, String.valueOf(userId));
    }

    /**
     * 추가할 extraClaims를 포함하여 UserDetails객체 정보를 Claim에 담아 Jwt를 생성한다.
     *
     * @return String token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return generateToken(extraClaims, userDetails.getUsername());
    }

    /**
     * Refresh 토큰 생성
     */
    public String generateRefreshToken(String userId) {
        String refreshToken = Jwts
            .builder()
            .setSubject(userId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpireMinutes))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();

        // redis에 저장
        refreshTokenRepository.save(RefreshTokenRedisData.builder()
            .id(userId)
            .refreshToken(refreshToken)
            .build());

        return refreshToken;
    }

    public String generateRefreshToken(long userId) {
        return generateRefreshToken(String.valueOf(userId));
    }

    /**
     * Refresh 토큰 생성
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(userDetails.getUsername());
    }

    /**
     * Jwt 토큰의 유효성을 검토한다.
     *
     * @return boolean
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Jwt 토큰의 유효기간을 점검한다.
     *
     * @return boolean
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Jwt 토큰의 유효기간을 추출한다.
     *
     * @return Date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Jwt 토큰의 모든 Claim을 추출한다.
     *
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }
}
