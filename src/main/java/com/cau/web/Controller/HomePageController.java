package com.cau.web.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cau.web.dto.ApiResponseNormal;
import com.cau.web.dto.ArticleSearchRequest;
import com.cau.web.entity.Article;
import com.cau.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomePageController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/homepage")
    public ApiResponseNormal<Page<Article>> getHomepageContent(@RequestBody ArticleSearchRequest request) {
        if (request.isEmpty()) {
            return new ApiResponseNormal<>(200, articleService.getAllArticles(request.getPageNumber(), request.getPageSize()), "检索成功"
        );
        }
        return new ApiResponseNormal<>(200, articleService.searchArticles(
                request.getTitle(),
                request.getAuthor(),
                request.getInfo_type(),
                request.getPostAgency(),
                request.getText(),
                request.getStartDate(),
                request.getEndDate(),
                request.getNation(),
                request.getDomain(),
                request.getSubject(),
                request.getPageNumber(),
                request.getPageSize()), "检索成功");
    }
}
