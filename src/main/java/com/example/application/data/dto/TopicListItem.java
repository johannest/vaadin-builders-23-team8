package com.example.application.data.dto;

import com.example.application.data.entity.Category;
import com.example.application.data.entity.Status;
import com.example.application.data.entity.Vaadiner;

public class TopicListItem {
    private int upvoteCount;
    private long id;
    private String title;
    private String description;
    private int commentCount;
    private Category category;
    private Status status;
    private Vaadiner answerer;

    public int getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Vaadiner getAnswerer() {
        return answerer;
    }

    public void setAnswerer(Vaadiner answerer) {
        this.answerer = answerer;
    }
}
