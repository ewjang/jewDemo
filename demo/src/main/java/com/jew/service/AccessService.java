package com.jew.service;

import java.util.ArrayList;

import com.jew.models.Member;
import com.jew.models.Menu;

public interface AccessService {

    
	public int menuInsert(Menu menu) throws Exception;

    public Member getUserInfo(String userId) throws Exception;
   
	public ArrayList<Menu> myMenuList(String userId) throws Exception;
}