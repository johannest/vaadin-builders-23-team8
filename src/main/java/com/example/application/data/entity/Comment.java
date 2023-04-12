package com.example.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Comment extends AbstractEntity {

    @ManyToOne
    private Topic topic;
    private String content;
    private LocalDateTime timestamp;
    @ManyToOne
    private Vaadiner commenter;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Vaadiner getCommenter() {
        return commenter;
    }

    public void setCommenter(Vaadiner commenter) {
        this.commenter = commenter;
    }
}
