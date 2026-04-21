package com.jew.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jew.models.Member;
import com.jew.models.Menu;

@Mapper
@Repository("com.jew.mapper.AccessMapper")
public interface AccessMapper {

     public int menuInsert(Menu menu) throws Exception;

     public ArrayList<Menu> myMenuList(String userId) throws Exception;

     public Member getUserInfo(String userId) throws Exception;

}
