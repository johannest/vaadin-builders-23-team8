package com.example.application.views.main.components;

import com.vaadin.flow.component.html.Div;

public class UpVote extends Div {

    private int count;
    public UpVote(Long topicId, int initialCount) {
        this.count = initialCount;
        setText(String.valueOf(this.count));
        setHeight("64px");
        setWidth("64px");
    }
}
