package com.cau.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleRatingMapper {

    @Select("SELECT COUNT(*) > 0 FROM article_rating WHERE article_id = #{articleId} AND expert_id = #{expertId}")
    boolean existsByArticleIdAndExpertId(@Param("articleId") Integer articleId, @Param("expertId") Integer expertId);

    @Insert("INSERT INTO article_rating (article_id, expert_id, innovation_score, disruption_score, frontier_score, industry_impact_score) VALUES (#{articleId}, #{expertId}, #{innovationScore}, #{disruptionScore}, #{frontierScore}, #{industryImpactScore})")
    void insert(@Param("articleId") Integer articleId, @Param("expertId") Integer expertId, @Param("innovationScore") int innovationScore, @Param("disruptionScore") int disruptionScore, @Param("frontierScore") int frontierScore, @Param("industryImpactScore") int industryImpactScore);

    @Select("SELECT AVG((innovation_score + disruption_score + frontier_score + industry_impact_score) / 4.0) FROM article_rating WHERE article_id = #{articleId}")
    double calculateAverageValueScore(@Param("articleId") Integer articleId);
}
