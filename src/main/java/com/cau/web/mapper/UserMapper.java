package com.cau.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cau.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
