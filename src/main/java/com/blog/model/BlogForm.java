package com.blog.model;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.util.Date;

public class BlogForm {
    private Long id;
    private String title;

    public BlogForm(Long id, String title, String brief, String content, Category category, MultipartFile img) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.category = category;
        this.img = img;
    }

    private String brief;
    private String content;

    public BlogForm(Long id, String title, String brief, String content, Category category, Date date, MultipartFile img) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.category = category;
        this.date = date;
        this.img = img;
    }

    public BlogForm(String title, String brief, String content, Category category, Date date, MultipartFile img) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.category = category;
        this.date = date;
        this.img = img;
    }

    private Category category;
    private Date date;
    private MultipartFile img;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BlogForm() {
    }

    public BlogForm(Long id, String title, String brief, String content, MultipartFile img) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
    }

    public BlogForm(String title, String brief, String content, MultipartFile img) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.img = img;
    }

    public BlogForm(Long id, String title, String brief, String content) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
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

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
