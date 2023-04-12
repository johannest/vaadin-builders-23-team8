package com.example.application.views.main.components;

import com.vaadin.flow.component.html.Div;

public class CommentIndicator extends Div {

    private Long topicId;
    private int commentCount;

    public CommentIndicator(Long topicId, int initialCommentCount) {
        this.topicId = topicId;
        this.commentCount = initialCommentCount;

        setText(String.valueOf(commentCount));
    }
}
