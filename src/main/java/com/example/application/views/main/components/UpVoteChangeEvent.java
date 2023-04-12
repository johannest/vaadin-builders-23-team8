package com.example.application.views.main.components;

import com.vaadin.flow.component.ComponentEvent;

public class UpVoteChangeEvent extends ComponentEvent<UpVote> {

    private Long topicId;
    private boolean enabled;

    public UpVoteChangeEvent(UpVote upVote, Long topicId, boolean enabled) {
        super(upVote, false);
        this.topicId = topicId;
        this.enabled = enabled;
    }

    public Long getTopicId() {
        return topicId;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
