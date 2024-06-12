package com.cau.web.Controller;

import com.cau.web.dto.AverageScoreResponse;
import com.cau.web.dto.ApiResponseNormal;
import com.cau.web.dto.RatingRequest;
import com.cau.web.service.ArticleRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private ArticleRatingService ratingService;

    @Secured("ROLE_EXPERT")
    @PostMapping("/{articleId}")
    public ApiResponseNormal<Void> rateArticle(@PathVariable Integer articleId, @RequestBody RatingRequest ratingRequest) {
        ratingService.rateArticle(articleId, ratingRequest.getExpertId(), ratingRequest.getInnovationScore(), ratingRequest.getDisruptionScore(), ratingRequest.getFrontierScore(), ratingRequest.getIndustryImpactScore());
        return new ApiResponseNormal<>(200, null, "评分已保存");
    }

    @GetMapping("/average/{articleId}")
    public ApiResponseNormal<AverageScoreResponse> getAverageValueScore(@PathVariable Integer articleId) {
        AverageScoreResponse averageScores = ratingService.getAverageValueScore(articleId);
        return new ApiResponseNormal<>(200, averageScores, "获取平均评分成功");
    }
}
