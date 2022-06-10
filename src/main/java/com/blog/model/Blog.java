package com.blog.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String brief;
    private String content;
    private String img;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog(String title, String brief, String content, String img, Date date, Category category) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
        this.date = date;
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Blog(Long id, String title, String brief, String content, String img, Date date, Category category) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
        this.date = date;
        this.category = category;
    }

    public Blog(String title, String brief, String content, String img, Category category) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
        this.category = category;
    }

    public Blog(Long id, String title, String brief, String content, String img, Category category) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Blog() {
    }

    public Blog(Long id, String title, String brief, String content, String img) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
    }

    public Blog(String title, String brief, String content, String img) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
