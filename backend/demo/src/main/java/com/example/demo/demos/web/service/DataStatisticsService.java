package com.example.demo.demos.web.service;

import com.example.demo.demos.web.pojo.VisitCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataStatisticsService {
    Long recordVisit();
    List<VisitCount> getTopTenData();
}
