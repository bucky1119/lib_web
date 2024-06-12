package com.cau.web.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ArticleSearchRequest {
    private String title;
    private String author;
    private String info_type;
    private String postAgency;
    private String text;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private String nation;
    private String domain;
    private String subject;
    private int pageNumber;
    private int pageSize;

    // Getters and setters...

    public boolean isEmpty() {
        return (title == null || title.isEmpty()) &&
                (author == null || author.isEmpty()) &&
                (info_type == null || info_type.isEmpty()) &&
                (postAgency == null || postAgency.isEmpty()) &&
                (text == null || text.isEmpty()) &&
                startDate == null &&
                endDate == null &&
                (nation == null || nation.isEmpty()) &&
                (domain == null || domain.isEmpty()) &&
                (subject == null || subject.isEmpty());
    }

    // Getters and setters...

    public String getInfo_type() {
        return info_type;
    }

    public void setInfo_type(String info_type) {
        this.info_type = info_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostAgency() {
        return postAgency;
    }

    public void setPostAgency(String postAgency) {
        this.postAgency = postAgency;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
