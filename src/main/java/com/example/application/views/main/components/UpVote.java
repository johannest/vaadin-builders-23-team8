package com.example.application.views.main.components;

import com.example.application.data.service.TopicService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpVote extends Div {

    private static final Logger LOG = LoggerFactory.getLogger(UpVote.class);

    private final TopicService topicService;
    private int count;
    private final Span countText;
    private com.example.application.data.entity.UpVote userUpVote;

    public UpVote(Long topicId, int initialCount, TopicService topicService) {
        this.count = initialCount;
        this.topicService = topicService;
        setClassName("up-vote");

        Icon upIcon = VaadinIcon.ANGLE_UP.create();
        countText = new Span();
        refresh();
        add(upIcon, countText);

        //TODO Add Broadcaster

        try {
            userUpVote = topicService.getUpVote(topicId);
        } catch (RuntimeException e) {
            LOG.warn("Error while searching for up-votes", e);
            //ignore if user not found
        }
        if (userUpVote != null) {
            addClassName("active");
        }

        addClickListener(click -> saveUpVote(topicId));
    }

    private void saveUpVote(Long topicId) {
        if (userUpVote == null) {
            userUpVote = topicService.saveUpVote(topicId);
            addClassName("active");
            count++;
        } else {
            topicService.removeUpVote(userUpVote);
            userUpVote = null;
            removeClassName("active");
            count--;
        }
        refresh();
        ComponentUtil.fireEvent(UI.getCurrent(), new UpVoteChangeEvent(this, topicId, userUpVote != null));
    }

    private void refresh() {
        countText.setText(String.valueOf(count));
    }
}
