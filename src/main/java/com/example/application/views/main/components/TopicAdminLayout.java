package com.example.application.views.main.components;

import com.example.application.data.dto.TopicListItem;
import com.example.application.data.entity.Vaadiner;
import com.example.application.data.service.TopicService;
import com.example.application.data.service.VaadinerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class TopicAdminLayout extends TopicLayout {

    private final TopicService topicService;
    private final VaadinerService vaadinerService;

    public TopicAdminLayout(TopicService topicService, VaadinerService vaadinerService) {
        super(topicService);
        this.topicService = topicService;
        this.vaadinerService = vaadinerService;
    }

    @Override
    protected ComponentRenderer<Component, TopicListItem> createTopicItemRenderer() {
        return new ComponentRenderer<>(
                topic -> {
                    var cardLayout = new HorizontalLayout();
                    var upvoteCounter = new UpVote(topic.getId(), topic.getUpvoteCount());

                    var infoLayout = new VerticalLayout();
                    infoLayout.setSpacing(false);
                    infoLayout.setPadding(false);
                    // TODO should be an anchor probably
                    infoLayout.add(new HorizontalLayout(createBadge(topic), new H4(topic.getTitle())));

                    var description = new Span(topic.getDescription());
                    description.setClassName("topic-item-description");
                    infoLayout.add(description);
                    infoLayout.add(new HorizontalLayout(createAssigneeSelect(topic), createAnsweredButton(topic)));

                    // TODO status
                    var commentIndicator = new CommentIndicator(topic.getId(), topic.getCommentCount());
                    cardLayout.add(upvoteCounter, infoLayout, commentIndicator);
                    cardLayout.setClassName("topic-item");
                    cardLayout.setAlignItems(Alignment.CENTER);
                    return cardLayout;
                });
    }

    private Component createAssigneeSelect(TopicListItem topic) {
        ComboBox<Vaadiner> assigneeSelect = new ComboBox<>();
        assigneeSelect.setPlaceholder("Assign topic to: ");
        assigneeSelect.setItems(vaadinerService.listAllLeaders());
        assigneeSelect.setValue(topic.getAnswerer());
        assigneeSelect.addValueChangeListener(e -> {
            topicService.assign(topic, assigneeSelect.getValue());
            Notification.show("Topic assigned and saved.");
            refresh();
        });
        return assigneeSelect;
    }

    private Component createAnsweredButton(TopicListItem topic) {
        Button answeredButton = new Button("Mark as answered");
        answeredButton.setIcon(VaadinIcon.CHECK_SQUARE_O.create());
        answeredButton.addClickListener(e -> {
            topicService.answered(topic);
            Notification.show("Topic saved.");
            refresh();
        });
        return answeredButton;
    }
}
