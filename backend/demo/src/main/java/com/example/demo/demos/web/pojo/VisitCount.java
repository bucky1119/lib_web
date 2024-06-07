package com.example.demo.demos.web.pojo;

import java.util.Date;

public class VisitCount {
    private Long id; // 对应于表中的id字段，使用Java的包装类型Long以允许null值
    private Date date; // 对应于表中的date字段，使用java.util.Date来处理日期
    private Long count; // 对应于表中的count字段

    // 无参构造方法
    public VisitCount() {
    }

    // 全参数构造方法
    public VisitCount(Long id, Date date, Long count) {
        this.id = id;
        this.date = date;
        this.count = count;
    }

    // id字段的getter方法
    public Long getId() {
        return id;
    }

    // id字段的setter方法
    public void setId(Long id) {
        this.id = id;
    }

    // date字段的getter方法
    public Date getDate() {
        return date;
    }

    // date字段的setter方法
    public void setDate(Date date) {
        this.date = date;
    }

    // count字段的getter方法
    public Long getCount() {
        return count;
    }

    // count字段的setter方法
    public void setCount(Long count) {
        this.count = count;
    }

    // 重写toString方法以方便打印和查看对象信息
    @Override
    public String toString() {
        return "VisitCount{" +
                "id=" + id +
                ", date=" + date +
                ", count=" + count +
                '}';
    }
}