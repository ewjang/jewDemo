package com.jew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jew.models.Menu;


@Mapper //Mybatis 라이브러리 설정후 추가
@Repository("com.jew.mapper.HomeMapper")
public interface HomeMapper {

    public int getQueryTest(int menuNo) throws Exception;
    
}
