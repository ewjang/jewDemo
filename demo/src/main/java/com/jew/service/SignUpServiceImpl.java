package com.jew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jew.mapper.SignUpMapper;
import com.jew.models.Member;

@Service
public class SignUpServiceImpl implements SignUpService {
    
    @Autowired
	SignUpMapper signUpMapper;
	
	@Transactional
	public void signup(Member member) throws Exception {
		signUpMapper.signup(member);
	}
}
