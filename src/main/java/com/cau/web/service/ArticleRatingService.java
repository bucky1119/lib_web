package com.cau.web.service;

public interface ArticleRatingService {
    void rateArticle(Integer articleId, Integer expertId, int innovationScore, int disruptionScore, int frontierScore, int industryImpactScore);
    double getAverageValueScore(Integer articleId);
}
