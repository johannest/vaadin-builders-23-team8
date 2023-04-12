package com.example.application.views.main;

import com.example.application.data.service.TopicService;
import com.example.application.views.main.components.TopicForm;
import com.example.application.views.main.components.TopicLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Main")
@Route(value = "", layout = AbstractLayout.class)
@PermitAll
public class MainView extends HorizontalLayout {

    @Autowired
    private TopicService topicService;
    private final TopicForm topicForm;
    private final TopicLayout topicLayout;

    public MainView() {
        setClassName("main");
        topicForm = new TopicForm(topicService);
        topicLayout = new TopicLayout(topicService);
        add(topicLayout, topicForm);
    }
}
