package com.cau.web.Controller;

import com.cau.web.entity.Article;
import com.cau.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ArticleService articleService; // 名为ArticleService的服务类来处理文章相关的业务逻辑

    // 搜索文章
    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) String text,
                                        @RequestParam(required = false) LocalDate startDate,
                                        @RequestParam(required = false) LocalDate endDate,
                                        @RequestParam(required = false) String nation,
                                        @RequestParam(required = false) String domain,
                                        @RequestParam(required = false) String subject) {
        List<Article> searchResults = articleService.searchArticles(title,author,text,startDate,endDate,nation,domain,subject); // 根据关键词搜索文章
        return searchResults;
    }
}

