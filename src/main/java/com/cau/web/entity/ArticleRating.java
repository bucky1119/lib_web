package com.cau.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("article_rating")
public class ArticleRating {
    private Long id;
    private Long articleId;
    private Long expertId;
    private int innovationScore;
    private int disruptionScore;
    private int frontierScore;
    private int industryImpactScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public int getInnovationScore() {
        return innovationScore;
    }

    public void setInnovationScore(int innovationScore) {
        this.innovationScore = innovationScore;
    }

    public int getDisruptionScore() {
        return disruptionScore;
    }

    public void setDisruptionScore(int disruptionScore) {
        this.disruptionScore = disruptionScore;
    }

    public int getFrontierScore() {
        return frontierScore;
    }

    public void setFrontierScore(int frontierScore) {
        this.frontierScore = frontierScore;
    }

    public int getIndustryImpactScore() {
        return industryImpactScore;
    }

    public void setIndustryImpactScore(int industryImpactScore) {
        this.industryImpactScore = industryImpactScore;
    }
}
