package com.cau.web.dto;

public class AverageScoreResponse {
    private double averageInnovationScore;
    private double averageDisruptionScore;
    private double averageFrontierScore;
    private double averageIndustryImpactScore;
    private double averageValueScore;

    // Getters and Setters

    public double getAverageInnovationScore() {
        return averageInnovationScore;
    }

    public void setAverageInnovationScore(double averageInnovationScore) {
        this.averageInnovationScore = averageInnovationScore;
    }

    public double getAverageDisruptionScore() {
        return averageDisruptionScore;
    }

    public void setAverageDisruptionScore(double averageDisruptionScore) {
        this.averageDisruptionScore = averageDisruptionScore;
    }

    public double getAverageFrontierScore() {
        return averageFrontierScore;
    }

    public void setAverageFrontierScore(double averageFrontierScore) {
        this.averageFrontierScore = averageFrontierScore;
    }

    public double getAverageIndustryImpactScore() {
        return averageIndustryImpactScore;
    }

    public void setAverageIndustryImpactScore(double averageIndustryImpactScore) {
        this.averageIndustryImpactScore = averageIndustryImpactScore;
    }

    public double getAverageValueScore() {
        return averageValueScore;
    }

    public void setAverageValueScore(double averageValueScore) {
        this.averageValueScore = averageValueScore;
    }
}
