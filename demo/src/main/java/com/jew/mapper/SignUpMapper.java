package com.jew.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jew.models.Member;


@Mapper //Mybatis 라이브러리 설정후 추가
@Repository("com.jew.mapper.SignUpMapper")
public interface SignUpMapper {

    public void signup(Member member) throws Exception;

    public int checkUserId(String userId) throws Exception;

}
