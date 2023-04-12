package com.example.application.views.main.components;

import com.example.application.data.entity.Comment;
import com.example.application.data.entity.Topic;
import com.example.application.data.service.TopicService;
import com.example.application.data.service.VaadinerService;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageInputI18n;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class CommentLayout extends VerticalLayout {
    private Topic topic;
    private TopicService topicService;
    private VaadinerService vaadinerService;

    private MessageList commentList;
    private MessageInput commentInput;

    public CommentLayout(Topic topic, TopicService topicService, VaadinerService vaadinerService) {
        this.topic = topic;
        this.topicService = topicService;
        this.vaadinerService = vaadinerService;
        init();
    }

    private void init() {
        commentList = new MessageList();
        refreshComments();

        commentInput = new MessageInput();
        var messageInputTexts = new MessageInputI18n();
        messageInputTexts.setMessage("Enter comment");
        messageInputTexts.setSend("Comment");
        commentInput.setI18n(messageInputTexts);
        commentInput.addSubmitListener(submitEvent -> {
            var comment = new Comment();
            comment.setCommenter(vaadinerService.listAll().get(0)); // TODO
            comment.setTimestamp(LocalDateTime.now());
            comment.setTopic(topic);
            comment.setContent(submitEvent.getValue());
            topicService.saveComment(comment);
            refreshComments();
        });
        add(commentInput, commentList);
    }

    public void refreshComments() {
        var comments = topic.getComments();
        commentList.setItems(commentsToMessageListItems(comments));
    }

    private List<MessageListItem> commentsToMessageListItems(List<Comment> commentList) {
        return commentList.stream().map(this::commentToMessageListItem).collect(Collectors.toList());
    }

    private MessageListItem commentToMessageListItem(Comment comment) {
        return new MessageListItem(comment.getContent(), comment.getTimestamp().toInstant(ZoneOffset.UTC),
                comment.getCommenter().getName());
    }
}
