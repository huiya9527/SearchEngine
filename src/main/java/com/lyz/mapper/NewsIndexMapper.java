package com.lyz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NewsIndexMapper {

    @Insert("insert into newsindex (word, appearNum, content) values (#{word}, #{appearNum}, #{content})")
    void insert(@Param("word")String word, @Param("appearNum")int appearNum, @Param("content")String content);
}
