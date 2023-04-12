package com.example.application.views.main;

import com.example.application.data.service.TopicService;
import com.example.application.views.main.components.EventLayout;
import com.example.application.views.main.components.TopicForm;
import com.example.application.views.main.components.TopicLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Topics")
@Route(value = "", layout = AbstractLayout.class)
@PermitAll
public class MainView extends VerticalLayout {

    private final TopicService topicService;
    private final TopicForm topicForm;
    private final TopicLayout topicLayout;
    private final EventLayout eventLayout;

    @Autowired
    public MainView(TopicService topicService) {
        this.topicService = topicService;
        setClassName("main");

        eventLayout = new EventLayout();

        topicForm = new TopicForm(topicService);
        topicLayout = new TopicLayout(topicService);
        setHeightFull();
        add(topicLayout, topicForm);

        var content = new HorizontalLayout(topicLayout, topicForm);
        content.setClassName("topic-content");
        content.setSizeFull();
        content.setPadding(false);

        add(eventLayout, content);
    }
}
