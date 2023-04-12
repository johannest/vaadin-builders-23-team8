package com.example.application.views.main.components;

import com.example.application.data.dto.TopicListItem;
import com.example.application.data.service.TopicService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class TopicLayout extends VerticalLayout {

    private TopicService topicService;

    private VirtualList<TopicListItem> topicList;

    public TopicLayout(TopicService topicService) {
        this.topicService = topicService;
        topicList = new VirtualList<>();
        topicList.setRenderer(createTopicItemRenderer());
        topicList.setItems(topicService.getAllTopicsSimplified());
        add(new H2("This is the Topic Layout"));

        add(topicList);
    }

    private ComponentRenderer<Component, TopicListItem> createTopicItemRenderer() {
        return new ComponentRenderer<>(
                topic -> {
                    var cardLayout = new HorizontalLayout();
                    var upvoteCounter = new UpVote(topic.getId(), topic.getUpvoteCount());
                    cardLayout.setMargin(true);

                    VerticalLayout infoLayout = new VerticalLayout();
                    infoLayout.setSpacing(false);
                    infoLayout.setPadding(false);
                    infoLayout.add(new H4(topic.getTitle())); // TODO should be an anchor probably
                    infoLayout.add(new Span(topic.getDescription()));

                    // TODO status
                    // TODO comment count
                    cardLayout.add(upvoteCounter, infoLayout);
                    return cardLayout;
                });
    }

}
