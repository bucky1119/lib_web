package com.cau.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cau.web.dto.AverageScoreResponse;
import com.cau.web.entity.ArticleRating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRatingMapper extends BaseMapper<ArticleRating> {

    @Select("SELECT COUNT(*) > 0 FROM article_rating WHERE article_id = #{articleId} AND expert_id = #{expertId}")
    boolean existsByArticleIdAndExpertId(@Param("articleId") Integer articleId, @Param("expertId") Integer expertId);

    @Insert("INSERT INTO article_rating (article_id, expert_id, innovation_score, disruption_score, frontier_score, industry_impact_score, additional_score) VALUES (#{articleId}, #{expertId}, #{innovationScore}, #{disruptionScore}, #{frontierScore}, #{industryImpactScore}, #{additionalScore})")
    void insert(@Param("articleId") Integer articleId, @Param("expertId") Integer expertId, @Param("innovationScore") int innovationScore, @Param("disruptionScore") int disruptionScore, @Param("frontierScore") int frontierScore, @Param("industryImpactScore") int industryImpactScore, @Param("additionalScore") int additionalScore);

    @Select("SELECT AVG(innovation_score) AS averageInnovationScore, AVG(disruption_score) AS averageDisruptionScore, AVG(frontier_score) AS averageFrontierScore, AVG(industry_impact_score) AS averageIndustryImpactScore, AVG(additional_score) AS averageAdditionalScore, AVG(innovation_score*0.25 + disruption_score*0.25 + frontier_score*0.25 + industry_impact_score*0.25 + additional_score*0) AS averageValueScore FROM article_rating WHERE article_id = #{articleId}")
    AverageScoreResponse calculateAverageScores(@Param("articleId") Integer articleId);

    @Select("SELECT ar.*, u.username AS expertUsername FROM article_rating ar JOIN user u ON ar.expert_id = u.id WHERE ar.article_id = #{articleId}")
    List<ArticleRating> findAllByArticleId(@Param("articleId") Integer articleId);

}
