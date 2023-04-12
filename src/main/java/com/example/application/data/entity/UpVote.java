package com.example.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class UpVote extends AbstractEntity {

    private LocalDate timestamp;

    @ManyToOne
    private Topic topic;
    @ManyToOne
    private Vaadiner voter;

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Vaadiner getVoter() {
        return voter;
    }

    public void setVoter(Vaadiner voter) {
        this.voter = voter;
    }
}
