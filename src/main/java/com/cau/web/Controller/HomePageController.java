package com.cau.web.Controller;

import com.cau.web.entity.Article;
import com.cau.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    private ArticleService articleService; // 假设有一个名为ArticleService的服务类来处理文章相关的业务逻辑

    // 获取时间降序全部文章或根据筛选条件搜索文章
    @GetMapping("/homepage")
    public List<Article> getHomepageContent(@RequestParam(required = false) String title,
                                            @RequestParam(required = false) String author,
                                            @RequestParam(required = false) String postAgency,
                                            @RequestParam(required = false) String text,
                                            @RequestParam(required = false) LocalDate startDate,
                                            @RequestParam(required = false) LocalDate endDate,
                                            @RequestParam(required = false) String nation,
                                            @RequestParam(required = false) String domain,
                                            @RequestParam(required = false) String subject) {
        // 如果没有筛选条件，则获取全部文章
        if (title == null && author == null && postAgency == null && text == null && startDate == null && endDate == null && nation == null && domain == null && subject == null) {
            return articleService.getAllArticles(); // 获取最新科技报道
        }
        // 根据筛选条件搜索文章
        return articleService.searchArticles(title, author, postAgency, text, startDate, endDate, nation, domain, subject);
    }
}
