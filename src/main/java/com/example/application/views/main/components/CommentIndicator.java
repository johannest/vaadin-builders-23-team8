package com.example.application.views.main.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;

public class CommentIndicator extends Div {

    private Long topicId;
    private int commentCount;

    public CommentIndicator(Long topicId, int initialCommentCount) {
        this.topicId = topicId;
        this.commentCount = initialCommentCount;
        var container = new Div();
        container.add(VaadinIcon.COMMENTS_O.create());
        container.add(String.valueOf(commentCount));
        container.getStyle().set("text-align", "center");
        add(container);
    }
}
