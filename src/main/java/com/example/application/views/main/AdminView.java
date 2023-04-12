package com.example.application.views.main;

import com.example.application.data.service.TopicService;
import com.example.application.data.service.VaadinerService;
import com.example.application.views.main.components.TopicAdminLayout;
import com.example.application.views.main.components.TopicLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
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

        H1 topicAdministration = new H1("Topic administration");
        topicAdministration.getStyle().set("text-align", "center");
        add(topicAdministration);
        addClassName("event-header");

        topicLayout = new TopicAdminLayout(topicService, vaadinerService);
        add(topicLayout);
        setSizeFull();
    }
}
