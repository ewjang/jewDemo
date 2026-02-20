package com.jew.restController;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jew.mapper.RefreshTokenMapper;
import com.jew.models.JwtResponse;
import com.jew.models.LoginRequest;
import com.jew.models.RefreshToken;
import com.jew.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenMapper refreshTokenMapper;

    /**
     * 로그인 → Access Token + Refresh Token 발급
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 1. 사용자 인증
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // 2. Access Token 생성
            String accessToken = jwtUtil.generateAccessToken(request.getUsername());

            // 3. Refresh Token 생성
            String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

            // 4. 기존 Refresh Token 삭제 후 새로 저장
            refreshTokenMapper.deleteByUserId(request.getUsername());

            RefreshToken tokenEntity = new RefreshToken();
            tokenEntity.setUSER_ID(request.getUsername());
            tokenEntity.setTOKEN(refreshToken);
            tokenEntity.setEXPIRE_DT(
                new Timestamp(System.currentTimeMillis() + jwtUtil.getRefreshTokenExpiry()).toString()
            );
            refreshTokenMapper.insertToken(tokenEntity);

            // 5. Access Token 쿠키 (30분)
            ResponseCookie accessCookie = ResponseCookie.from("token", accessToken)
                    .httpOnly(false)
                    .path("/")
                    .maxAge(60 * 30)
                    .build();

            // 6. Refresh Token 쿠키 (7일, httpOnly)
            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(60 * 60 * 24 * 7)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(new JwtResponse(accessToken));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 올바르지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("로그인 처리 중 오류가 발생했습니다.");
        }
    }

    /**
     * Refresh → Access Token 재발급
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        try {
            // 1. Refresh Token 존재 확인
            if (refreshToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Refresh Token이 없습니다."));
            }

            // 2. JWT 유효성 검증
            if (!jwtUtil.validateToken(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Refresh Token이 만료되었습니다."));
            }

            // 3. DB에 존재하는지 확인 (폐기된 토큰 방지)
            RefreshToken stored = refreshTokenMapper.findByToken(refreshToken);
            if (stored == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "유효하지 않은 Refresh Token입니다."));
            }

            // 4. 새 Access Token 발급
            String username = jwtUtil.extractUsername(refreshToken);
            String newAccessToken = jwtUtil.generateAccessToken(username);

            ResponseCookie accessCookie = ResponseCookie.from("token", newAccessToken)
                    .httpOnly(false)
                    .path("/")
                    .maxAge(60 * 30)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                    .body(new JwtResponse(newAccessToken));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "토큰 갱신에 실패했습니다."));
        }
    }

    /**
     * 로그아웃 → Refresh Token 삭제 + 쿠키 제거
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        try {
            // DB에서 Refresh Token 삭제
            if (refreshToken != null) {
                refreshTokenMapper.deleteByToken(refreshToken);
            }
        } catch (Exception e) {
            // 삭제 실패해도 쿠키는 제거
        }

        // Access Token 쿠키 제거
        ResponseCookie accessCookie = ResponseCookie.from("token", "")
                .httpOnly(false)
                .path("/")
                .maxAge(0)
                .build();

        // Refresh Token 쿠키 제거
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(Map.of("message", "로그아웃 되었습니다."));
    }
}
