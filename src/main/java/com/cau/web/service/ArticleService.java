package com.cau.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cau.web.entity.Article;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService extends IService<Article> {
    // 获取最新科技报道的方法
    List<Article> getLatestArticles();

    // 创建文章
    boolean saveArticle(Article article);

    // 根据ID获取文章
    Article getArticleById(Long id);

    // 获取所有文章
    List<Article> getAllArticles();

    // 更新文章
    boolean updateArticle(Article article);

    // 根据ID删除文章
    boolean deleteArticleById(Long id);


    // 搜索文章
    List<Article> searchArticles(String title,String author,String text, LocalDate startDate, LocalDate endDate, String nation, String domain, String subject);

}
