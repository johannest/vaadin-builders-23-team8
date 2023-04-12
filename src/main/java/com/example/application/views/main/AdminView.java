package com.example.application.views.main;

import com.example.application.data.service.TopicService;
import com.example.application.data.service.VaadinerService;
import com.example.application.views.main.components.TopicAdminLayout;
import com.example.application.views.main.components.TopicLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@RolesAllowed({"admin"})
@Route(value = "admin", layout = AbstractLayout.class)
public class AdminView extends Div {

    private final TopicService topicService;
    private final TopicLayout topicLayout;

    @Autowired
    public AdminView(TopicService topicService, VaadinerService vaadinerService) {
        this.topicService = topicService;
        setClassName("admin");
        topicLayout = new TopicAdminLayout(topicService, vaadinerService);
        add(new Span("Topic administration"));
        add(topicLayout);
    }
}
