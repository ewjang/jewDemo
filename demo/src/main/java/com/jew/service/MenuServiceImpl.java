package com.jew.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jew.mapper.MenuMapper;
import com.jew.models.Menu;

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
	MenuMapper menuMapper;
	
	@Transactional
	public int menuInsert(Menu menu) throws Exception {
		return menuMapper.menuInsert(menu);
	}

    @Transactional
    public ArrayList<Menu> myMenuList(String userId) throws Exception {
        return menuMapper.myMenuList(userId);
    }
}
