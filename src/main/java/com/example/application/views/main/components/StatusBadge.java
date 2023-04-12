package com.example.application.views.main.components;

import com.example.application.data.entity.Status;
import com.vaadin.flow.component.html.Span;

public class StatusBadge extends Span {
    public StatusBadge(Status status) {
        setText(status.name());
        switch (status) {
            case NEW -> {
                getElement().getThemeList().add("badge");
            }
            case ASSIGNED -> {
                getElement().getThemeList().add("badge contrast");
            }
            case ANSWERED -> {
                getElement().getThemeList().add("badge success");
            }
        }
    }
}
