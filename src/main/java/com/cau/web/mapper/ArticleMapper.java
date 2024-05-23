package com.cau.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cau.web.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}

