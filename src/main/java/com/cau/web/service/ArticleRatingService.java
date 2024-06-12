package com.cau.web.service;

import com.cau.web.dto.AverageScoreResponse;

public interface ArticleRatingService {
    void rateArticle(Integer articleId, Integer expertId, int innovationScore, int disruptionScore, int frontierScore, int industryImpactScore);
    AverageScoreResponse getAverageValueScore(Integer articleId);
}
