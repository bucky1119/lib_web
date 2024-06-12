package com.cau.web.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cau.web.dto.ApiResponseNormal;
import com.cau.web.entity.Article;
import com.cau.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Secured("ROLE_LIBRARIAN")
    @PostMapping("/add")
    public ApiResponseNormal<Article> addArticle(@RequestBody Article article) {
        boolean isSuccess = articleService.saveArticle(article);
        if (isSuccess) {
            return new ApiResponseNormal<>(200, article, "文章已添加");
        } else {
            return new ApiResponseNormal<>(500, null, "文章添加失败");
        }
    }

    @Secured("ROLE_LIBRARIAN")
    @PutMapping("/{id}")
    public ApiResponseNormal<Article> updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        article.setId(id);
        boolean isSuccess = articleService.updateArticle(article);
        if (isSuccess) {
            return new ApiResponseNormal<>(200, article, "文章已更新");
        } else {
            return new ApiResponseNormal<>(500, null, "文章更新失败");
        }
    }

    @Secured("ROLE_LIBRARIAN")
    @DeleteMapping("/{id}")
    public ApiResponseNormal<Void> deleteArticle(@PathVariable Long id) {
        boolean isSuccess = articleService.deleteArticleById(id);
        if (isSuccess) {
            return new ApiResponseNormal<>(200, null, "文章已删除");
        } else {
            return new ApiResponseNormal<>(500, null, "文章删除失败");
        }
    }

    @GetMapping("/{id}")
    public ApiResponseNormal<Article> getArticleById(@PathVariable Integer id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            return new ApiResponseNormal<>(200, article, "获取文章成功");
        } else {
            return new ApiResponseNormal<>(404, null, "文章未找到");
        }
    }

}
