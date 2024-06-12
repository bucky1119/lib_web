package com.cau.web.service.impl;

import com.cau.web.mapper.ArticleRatingMapper;
import com.cau.web.service.ArticleRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleRatingServiceImpl implements ArticleRatingService {

    @Autowired
    private ArticleRatingMapper articleRatingMapper;

    @Override
    public void rateArticle(Integer articleId, Integer expertId, int innovationScore, int disruptionScore, int frontierScore, int industryImpactScore) {
        // 在插入评分之前，检查是否已经存在评分
        if (articleRatingMapper.existsByArticleIdAndExpertId(articleId, expertId)) {
            throw new RuntimeException("专家已对该文章评分");
        }

        // 插入评分
        articleRatingMapper.insert(articleId, expertId, innovationScore, disruptionScore, frontierScore, industryImpactScore);
    }

    @Override
    public double getAverageValueScore(Integer articleId) {
        return articleRatingMapper.calculateAverageValueScore(articleId);
    }
}
