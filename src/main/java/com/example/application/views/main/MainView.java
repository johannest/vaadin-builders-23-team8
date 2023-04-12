package com.example.application.views.main;

import com.example.application.views.main.components.TopicForm;
import com.example.application.views.main.components.TopicLayout;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Main")
@Route(value = "", layout = AbstractLayout.class)
@PermitAll
public class MainView extends HorizontalLayout {

    private TopicForm topicForm;
    private TopicLayout topicLayout;

    public MainView() {
       topicForm = new TopicForm();
       topicLayout = new TopicLayout();
       add(topicLayout, topicForm);
    }
}
