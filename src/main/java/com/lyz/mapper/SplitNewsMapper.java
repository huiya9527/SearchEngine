package com.lyz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SplitNewsMapper {
    @Insert("insert into splitnews (id, splitWordList) values (#{id}, #{splitWordList})")
    void insert(@Param("id")int id, @Param("splitWordList")String splitWordList);

    @Select("select max(id) from splitnews ")
    int findMaxId();

    @Select("select splitWordList from splitnews where id = #{id}")
    String selectById(@Param("id")int id);
}
