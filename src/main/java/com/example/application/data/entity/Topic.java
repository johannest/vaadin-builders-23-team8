package com.example.application.data.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Topic extends AbstractEntity {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(fetch = FetchType.EAGER)
    private List<UpVote> upVotes;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;
    @ManyToOne
    private Vaadiner submitter;
    @ManyToOne
    private Vaadiner answerer;

    private boolean anonymous = true;

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

    public List<UpVote> getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(List<UpVote> upVotes) {
        this.upVotes = upVotes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Vaadiner getAnswerer() {
        return answerer;
    }

    public void setAnswerer(Vaadiner answerer) {
        this.answerer = answerer;
    }

    public Vaadiner getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Vaadiner submitter) {
        this.submitter = submitter;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
