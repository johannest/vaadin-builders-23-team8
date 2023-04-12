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

import java.util.ArrayList;
import java.util.List;

public class TopicLayout extends VerticalLayout {

    private final TopicFilterBar topicSearch;
    private TopicService topicService;

    private VirtualList<TopicListItem> topicList;
    public TopicLayout(TopicService topicService) {
        this.topicService = topicService;
        topicList = new VirtualList<>();
        topicList.setRenderer(createTopicItemRenderer());
        topicList.setItems(createTestData());

        topicSearch = new TopicFilterBar();
        topicSearch.setWidth("100%");
        topicSearch.setSearchListener(((searchTerm, category) -> {
            System.out.println(searchTerm+" "+category);
        }));

        add(new H2("This is the Topic Layout"));
        add(topicSearch);
        add(topicList);
    }

    private List<TopicListItem> createTestData() {
        var topicList = new ArrayList<TopicListItem>();
        topicList.add(createListItem(1, "What happened?", "Everything was according to plan, but now it isn't. What happened?", 25));
        topicList.add(createListItem(2, "Why would you do such a thing?", "Everyone told you not to, but you did. Why?", 1));
        topicList.add(createListItem(3, "Where is this going?", "I feel lost, I'm not sure where I am and where this ship is headed to. Can you help me understand?", 10));
        topicList.add(createListItem(4, "What now?", "What is the plan to resolve the problem of there existing problems?", 8));
        return topicList;
    }

    private TopicListItem createListItem(long id, String title, String description, int upVoteCount) {
        var item = new TopicListItem();
        item.setId(id);
        item.setTitle(title);
        item.setDescription(description);
        item.setUpvoteCount(upVoteCount);

        return item;
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
                    var commentIndicator = new CommentIndicator(topic.getId(), topic.getCommentCount());
                    cardLayout.add(upvoteCounter, infoLayout, commentIndicator);
                    return cardLayout;
                });
    }

}
