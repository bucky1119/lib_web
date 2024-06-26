package com.cau.web.Controller;

import com.cau.web.dto.ApiResponseNormal;
import com.cau.web.dto.AverageScoreResponse;
import com.cau.web.dto.RatingRequest;
import com.cau.web.entity.ArticleRating;
import com.cau.web.service.ArticleRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private ArticleRatingService ratingService;

    @Secured({"ROLE_EXPERT", "ROLE_ADMIN"})
    @PostMapping("/{articleId}")
    public ApiResponseNormal<Void> rateArticle(@PathVariable Integer articleId, @RequestBody RatingRequest ratingRequest) {
        String result = ratingService.rateArticle(articleId, ratingRequest.getExpertId(), ratingRequest.getInnovationScore(), ratingRequest.getDisruptionScore(), ratingRequest.getFrontierScore(), ratingRequest.getIndustryImpactScore(), ratingRequest.getAdditionalScore());

        if ("专家已对该文章评分".equals(result)) {
            return new ApiResponseNormal<>(400, null, result);
        }

        return new ApiResponseNormal<>(200, null, result);
    }

    @GetMapping("/average/{articleId}")
    public ApiResponseNormal<AverageScoreResponse> getAverageValueScore(@PathVariable Integer articleId) {
        AverageScoreResponse averageScores = ratingService.getAverageValueScore(articleId);
        return new ApiResponseNormal<>(200, averageScores, "获取平均评分成功");
    }

    @Secured({"ROLE_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/history/{articleId}")
    public ApiResponseNormal<List<ArticleRating>> getRatingHistory(@PathVariable Integer articleId) {
        List<ArticleRating> ratingHistory = ratingService.getArticleRatingHistory(articleId);
        return new ApiResponseNormal<>(200, ratingHistory, "获取评分历史成功");
    }
}
