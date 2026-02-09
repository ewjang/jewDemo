package com.jew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jew.models.Member;

@Service
public interface SignUpService {

    @Autowired
	public void signup(Member member) throws Exception;
}
