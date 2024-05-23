package com.cau.web.Controller;

import com.cau.web.entity.Article;
import com.cau.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    private ArticleService articleService; // 假设有一个名为ArticleService的服务类来处理文章相关的业务逻辑

    // 获取时间降序全部文章
    @GetMapping("/homepage")
    public List<Article> getHomepageContent() {
        List<Article> allArticles = articleService.getAllArticles(); // 获取最新科技报道
        return allArticles;
    }

}

