package com.cau.web.dto;

public class RatingRequest {
    private Integer expertId;
    private int innovationScore;
    private int disruptionScore;
    private int frontierScore;
    private int industryImpactScore;

    // Getters and Setters

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
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
