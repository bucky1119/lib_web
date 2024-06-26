package com.cau.web.service.impl;

import com.cau.web.dto.AverageScoreResponse;
import com.cau.web.entity.ArticleRating;
import com.cau.web.mapper.ArticleRatingMapper;
import com.cau.web.service.ArticleRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRatingServiceImpl implements ArticleRatingService {

    @Autowired
    private ArticleRatingMapper articleRatingMapper;

    @Override
    public String rateArticle(Integer articleId, Integer expertId, int innovationScore, int disruptionScore, int frontierScore, int industryImpactScore, int additionalScore) {
        // 在插入评分之前，检查是否已经存在评分
        if (articleRatingMapper.existsByArticleIdAndExpertId(articleId, expertId)) {
            return "专家已对该文章评分";
        }

        // 插入评分
        articleRatingMapper.insert(articleId, expertId, innovationScore, disruptionScore, frontierScore, industryImpactScore, additionalScore);
        return "评分已保存";
    }

    @Override
    public AverageScoreResponse getAverageValueScore(Integer articleId) {
        return articleRatingMapper.calculateAverageScores(articleId);
    }

    @Override
    public List<ArticleRating> getArticleRatingHistory(Integer articleId) {
        return articleRatingMapper.findAllByArticleId(articleId);
    }
}
