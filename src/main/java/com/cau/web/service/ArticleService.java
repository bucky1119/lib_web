package com.cau.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cau.web.entity.Article;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService {
    boolean saveArticle(Article article);
    Article getArticleById(Integer id);
    Page<Article> getAllArticles(int pageNumber, int pageSize);
    boolean updateArticle(Article article);
    boolean deleteArticleById(Long id);
    List<Article> getLatestArticles();
    Page<Article> searchArticles(String title, String author, String info_type, String postAgency, String text, LocalDate startDate, LocalDate endDate, String nation, String domain, String subject, int pageNumber, int pageSize);
    Integer getMaxId();
}
