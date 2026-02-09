package com.jew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jew.mapper.HomeMapper;
import com.jew.models.Menu;

@Service
public class HomeServiceImpl implements HomeService {
    
    @Autowired
	HomeMapper homeMapper;
	
	@Transactional
	public int javaLec() throws Exception {
		int temp = 93;
		return homeMapper.getQueryTest(temp);
	}
}
