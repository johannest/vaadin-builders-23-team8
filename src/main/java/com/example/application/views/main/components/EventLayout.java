package com.example.application.views.main.components;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;

public class EventLayout extends VerticalLayout {

    public EventLayout() {
        addClassName("event-header");

        add(new H1("Town Hall meeting: " + LocalDate.now()));
    }
}
