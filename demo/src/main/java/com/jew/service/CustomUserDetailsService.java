package com.jew.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jew.mapper.LoginMapper;
import com.jew.models.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncode;
    private final LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Member member = loginMapper.findByUserId(username);
            if (member == null) {
                throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
            }
            return new User(member.getUSER_ID(), passwordEncode.encode(member.getPASSWORD()), Collections.emptyList());
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException("사용자 조회 중 오류 발생", e);
        }
    }
}
