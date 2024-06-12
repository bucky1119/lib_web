package com.cau.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cau.web.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT MAX(id) FROM web_data_table")
    Integer getMaxId();
}

