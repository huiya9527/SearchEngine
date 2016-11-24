package com.lyz.mapper;

import com.lyz.dao.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface NewsMapper {
    @Select("select id, type, title, url, content, time, comments_num from sports where id=#{id}")
    Item findById(@Param("id")int id);
}
