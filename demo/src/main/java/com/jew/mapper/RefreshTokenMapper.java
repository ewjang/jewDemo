package com.jew.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jew.models.RefreshToken;

@Mapper
@Repository("com.jew.mapper.RefreshTokenMapper")
public interface RefreshTokenMapper {

    // Refresh Token 저장
    void insertToken(RefreshToken refreshToken) throws Exception;

    // Token 값으로 조회
    RefreshToken findByToken(@Param("token") String token) throws Exception;

    // 사용자 ID로 기존 토큰 삭제 (로그인 시 기존 토큰 정리)
    void deleteByUserId(@Param("userId") String userId) throws Exception;

    // 특정 토큰 삭제 (로그아웃)
    void deleteByToken(@Param("token") String token) throws Exception;
}
