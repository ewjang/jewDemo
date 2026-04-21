package com.jew.service;

import java.util.ArrayList;

import com.jew.models.Menu;

public interface MenuService {

    //@Autowired
	public int menuInsert(Menu menu) throws Exception;

    //@Autowired
	public ArrayList<Menu> myMenuList(String userId) throws Exception;
}