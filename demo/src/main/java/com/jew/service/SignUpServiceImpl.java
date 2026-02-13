package com.jew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jew.mapper.SignUpMapper;
import com.jew.models.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    
    @Autowired
	SignUpMapper signUpMapper;
	
	private final PasswordEncoder passwordEncode;

	@Transactional
	public void signup(Member member) throws Exception {
		String encodedPassword = passwordEncode.encode(member.getPASSWORD());
		member.setPASSWORD(encodedPassword);
		signUpMapper.signup(member);
	}
}
