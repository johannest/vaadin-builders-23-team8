package com.example.application.views.main;

import com.example.application.data.entity.Topic;
import com.example.application.data.service.TopicService;
import com.example.application.data.service.VaadinerService;
import com.example.application.views.main.components.CommentLayout;
import com.example.application.views.main.components.StatusBadge;
import com.example.application.views.main.components.UpVote;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Topic")
@Route(value = "topic", layout = AbstractLayout.class)
@PermitAll
public class TopicView extends VerticalLayout implements HasUrlParameter<Long> {

    @Autowired
    private TopicService topicService;
    @Autowired
    private VaadinerService vaadinerService;
    private Topic topic;

    public TopicView() {
        addClassName("main");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (attachEvent.isInitialAttach()) {
            init();
        }
    }

    private void init() {
        var topicDetails = new HorizontalLayout();
        add(topicDetails);

        if (topic == null) {
            // TODO show error
            return;
        }
        var upVote = new UpVote(topic.getId(), topic.getUpVotes().size());
        var infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setPadding(false);
        infoLayout.add(new HorizontalLayout(new StatusBadge(topic.getStatus()), new H4(topic.getTitle())));
        infoLayout.add(new Span(topic.getDescription()));

        topicDetails.add(upVote, infoLayout);

        add(new CommentLayout(topic, topicService, vaadinerService));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long topicId) {
        this.topic = topicService.getTopicById(topicId);
    }
}
