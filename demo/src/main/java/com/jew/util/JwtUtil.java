package com.jew.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET = "mySecretKeyMySecretKey12345678901";  // 32bytes 이상 필요
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long ACCESS_TOKEN_EXPIRY = 1000 * 60 * 30;       // 30분
    private static final long REFRESH_TOKEN_EXPIRY = 1000 * 60 * 60 * 24 * 7; // 7일

    // Access Token 생성
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("type", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRY))
                .signWith(key)
                .compact();
    }

    // Refresh Token 생성
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
                .signWith(key)
                .compact();
    }

    // 토큰에서 사용자명 추출
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 만료된 토큰인지 확인
    public boolean isTokenExpired(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getRefreshTokenExpiry() {
        return REFRESH_TOKEN_EXPIRY;
    }
}
