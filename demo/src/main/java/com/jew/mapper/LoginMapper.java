package com.jew.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jew.models.Member;

@Mapper
@Repository("com.jew.mapper.LoginMapper")
public interface LoginMapper {

    public Member findByUserId(String userId) throws Exception;

}
