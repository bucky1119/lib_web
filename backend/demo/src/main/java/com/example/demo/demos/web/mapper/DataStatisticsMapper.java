package com.example.demo.demos.web.mapper;

import com.example.demo.demos.web.pojo.VisitCount;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DataStatisticsMapper {
    // 获取指定日期的访问量
    Long getVisitCountByDate(LocalDate date);

    // 为指定日期的访问量增加一
    void increaseVisitCount(LocalDate date);

    // 如果指定日期的记录不存在，则插入一条新记录
    void insertVisitCount(LocalDate date);

    List<VisitCount> selectTopTenData();
}
