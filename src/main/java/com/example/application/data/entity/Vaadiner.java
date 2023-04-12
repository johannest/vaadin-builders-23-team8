package com.example.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Vaadiner extends AbstractEntity {

    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String avatarBase64;

    @OneToMany
    private List<Topic> submittedTopics;
    @OneToMany
    private List<Topic> answererForTopics;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<UpVote> upVotes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    public List<Topic> getSubmittedTopics() {
        return submittedTopics;
    }

    public void setSubmittedTopics(List<Topic> submittedTopics) {
        this.submittedTopics = submittedTopics;
    }

    public List<Topic> getAnswererForTopics() {
        return answererForTopics;
    }

    public void setAnswererForTopics(List<Topic> answererForTopics) {
        this.answererForTopics = answererForTopics;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<UpVote> getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(List<UpVote> upVotes) {
        this.upVotes = upVotes;
    }
}
