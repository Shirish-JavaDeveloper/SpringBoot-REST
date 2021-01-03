package com.example.spring.rest.dto;

public class Post {
    private Integer id;
    private Integer userId;
    private String comments;

    public Post(Integer id, Integer userId, String comments) {
        this.id = id;
        this.userId = userId;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
