package com.example.application.views.main.components;

import com.example.application.data.dto.TopicListItem;
import com.example.application.data.entity.Vaadiner;
import com.example.application.data.service.TopicService;
import com.example.application.data.service.VaadinerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class TopicAdminLayout extends TopicLayout {

    private final VaadinerService vaadinerService;

    public TopicAdminLayout(TopicService topicService, VaadinerService vaadinerService) {
        super(topicService);
        this.vaadinerService = vaadinerService;
    }

    @Override
    protected ComponentRenderer<Component, TopicListItem> createTopicItemRenderer() {
        return new ComponentRenderer<>(
                topic -> {
                    var cardLayout = new HorizontalLayout();
                    var upvoteCounter = new UpVote(topic.getId(), topic.getUpvoteCount());
                    cardLayout.setMargin(true);

                    VerticalLayout infoLayout = new VerticalLayout();
                    infoLayout.setSpacing(false);
                    infoLayout.setPadding(false);
                    infoLayout.add(new H4(topic.getTitle())); // TODO should be an anchor probably
                    infoLayout.add(createBadge(topic));
                    infoLayout.add(new Span(topic.getDescription()));
                    infoLayout.add(createAssigneeSelect());

                    // TODO status
                    var commentIndicator = new CommentIndicator(topic.getId(), topic.getCommentCount());
                    cardLayout.add(upvoteCounter, infoLayout, commentIndicator);
                    return cardLayout;
                });
    }

    private Component createAssigneeSelect() {
        ComboBox<Vaadiner> assigneeSelect = new ComboBox<>();
        assigneeSelect.setItems(vaadinerService.listAllLeaders());
        return assigneeSelect;
    }
}
