package com.cau.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cau.web.entity.Article;
import com.cau.web.mapper.ArticleMapper;
import com.cau.web.service.ArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public boolean saveArticle(Article article) {
        return this.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        return this.getById(id);
    }

    @Override
    public List<Article> getAllArticles() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date"); // 按照创建时间降序排序
        return this.list(queryWrapper);
    }

    @Override
    public boolean updateArticle(Article article) {
        return this.updateById(article);
    }

    @Override
    public boolean deleteArticleById(Long id) {
        return this.removeById(id);
    }

    @Override
    public List<Article> getLatestArticles() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        queryWrapper.last("LIMIT 10"); // 假设获取最新的10篇文章

        return this.list(queryWrapper);
    }

    @Override
    public List<Article> searchArticles(String title, String author, String text,LocalDate startDate, LocalDate endDate, String nation, String domain, String subject)
    {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        // 添加标题条件
        if (title != null && !title.isEmpty()) {
            queryWrapper.like("title", title);
        }

        // 添加作者条件
        if (author != null && !author.isEmpty()) {
            queryWrapper.like("author", author);
        }

        //添加正文条件
        if (text != null && !text.isEmpty()) {
            queryWrapper.like("text", text);
        }

        // 添加日期范围条件
        if (startDate != null && endDate != null) {
            queryWrapper.between("date", startDate, endDate);
        } else if (startDate != null) {
            queryWrapper.ge("date", startDate);
        } else if (endDate != null) {
            queryWrapper.le("date", endDate);
        }

        // 添加国家条件
        if (nation != null && !nation.isEmpty()) {
            queryWrapper.eq("nation", nation);
        }

        // 添加领域条件
        if (domain != null && !domain.isEmpty()) {
            queryWrapper.eq("domain", domain);
        }

        // 添加主题条件
        if (subject != null && !subject.isEmpty()) {
            queryWrapper.eq("subject", subject);
        }

        return this.list(queryWrapper);
    }
}

