package com.example.application.views.main.components;

import com.example.application.data.dto.TopicListItem;
import com.example.application.data.service.TopicService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class TopicLayout extends VerticalLayout {

    private final TopicFilterBar topicSearch;
    private TopicService topicService;

    private VirtualList<TopicListItem> topicList;

    public TopicLayout(TopicService topicService) {
        this.topicService = topicService;
        setHeightFull();
        setClassName("topic-layout");

        topicList = new VirtualList<>();
        topicList.setClassName("topic-item-list");
        topicList.setRenderer(createTopicItemRenderer());

        topicList.setHeight("80vh");
        topicList.setItems(topicService.getAllTopicsSimplified());

        topicSearch = new TopicFilterBar();
        topicSearch.setWidth("100%");
        topicSearch.setSearchListener(((searchTerm, category) -> {
            topicList.setItems(topicService.searchTopics(searchTerm, category));
        }));

        add(topicSearch);
        add(topicList);

        ComponentUtil.addListener(UI.getCurrent(), CreateTopicEvent.class, event -> {
            topicList.setItems(topicService.getAllTopicsSimplified());
        });
    }

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

                    // TODO status
                    var commentIndicator = new CommentIndicator(topic.getId(), topic.getCommentCount());
                    cardLayout.add(upvoteCounter, infoLayout, commentIndicator);
                    cardLayout.setClassName("topic-item");
                    cardLayout.setAlignItems(Alignment.CENTER);
                    return cardLayout;
                });
    }

    protected static Span createBadge(TopicListItem topic) {
        Span badge = new Span(topic.getStatus().name());
        switch (topic.getStatus()) {
            case NEW -> {
                badge.getElement().getThemeList().add("badge");
            }
            case ASSIGNED -> {
                badge.getElement().getThemeList().add("badge contrast");
            }
            case ANSWERED -> {
                badge.getElement().getThemeList().add("badge success");
            }
        }
        return badge;
    }

    protected void refresh() {
        topicList.setItems(topicService.searchTopics(topicSearch.getSearchTerm(), topicSearch.getCategory()));
    }
}
