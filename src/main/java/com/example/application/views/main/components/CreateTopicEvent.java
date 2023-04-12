package com.example.application.views.main.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class CreateTopicEvent extends ComponentEvent<Component> {
    public CreateTopicEvent(Component source) {
        super(source, false);
    }
}
