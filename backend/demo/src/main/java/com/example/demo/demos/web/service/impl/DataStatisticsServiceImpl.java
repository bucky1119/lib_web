package com.example.demo.demos.web.service.impl;

import java.util.List;
import com.example.demo.demos.web.mapper.DataStatisticsMapper;
import com.example.demo.demos.web.pojo.VisitCount;
import com.example.demo.demos.web.service.DataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DataStatisticsServiceImpl implements DataStatisticsService {

    @Autowired
    private DataStatisticsMapper dataStatisticsMapper;

    @Override
    public Long recordVisit() {
        LocalDate today = LocalDate.now();
        Long visits = dataStatisticsMapper.getVisitCountByDate(today);

        if (visits == null) {
            // 当天记录不存在，插入新记录
            dataStatisticsMapper.insertVisitCount(today);
        }

        // 为当天的访问量加一
        dataStatisticsMapper.increaseVisitCount(today);
        return visits;
    }

    public DataStatisticsServiceImpl(DataStatisticsMapper dataStatisticsMapper) {
        this.dataStatisticsMapper = dataStatisticsMapper;
    }

    @Override
    public List<VisitCount> getTopTenData() {
        return dataStatisticsMapper.selectTopTenData();
    }
}