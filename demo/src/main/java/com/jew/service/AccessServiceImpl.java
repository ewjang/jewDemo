package com.jew.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jew.mapper.AccessMapper;
import com.jew.models.Member;
import com.jew.models.Menu;

@Service
public class AccessServiceImpl implements AccessService {
    
    @Autowired
	AccessMapper accessMapper;
	
	@Transactional
	public int menuInsert(Menu menu) throws Exception {
		return accessMapper.menuInsert(menu);
	}

    @Transactional
    public Member getUserInfo(String userId) throws Exception {
        return accessMapper.getUserInfo(userId);
    }

    @Transactional
    public ArrayList<Menu> myMenuList(String userId) throws Exception {
        return accessMapper.myMenuList(userId);
    }
}
