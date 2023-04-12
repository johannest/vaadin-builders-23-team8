package com.example.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Topic extends AbstractEntity {
    private String title;
    @OneToMany
    private List<UpVote> upVotes;
    @OneToMany
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
