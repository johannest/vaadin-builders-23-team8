package com.example.application.views.main.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class TopicForm extends VerticalLayout {

    public TopicForm() {
        addClassName("topic-form");

        var formTitle = new H2("Choose the topic");
        var formSubtitle = new Text("What do should we discuss?");

        var topicForm = new FormLayout();
        var topicTitle = new TextField();
        var topicDescription = new TextField();

        topicForm.addFormItem(topicTitle, "Topic");
        topicForm.addFormItem(topicDescription, "Question");
        topicForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        var submit = new Button("Submit", click -> {
            submitTopic();
        });

        setAlignSelf(Alignment.END, submit);
        setMaxWidth(400, Unit.PIXELS);


        add(formTitle, formSubtitle, topicForm, submit);
    }

    private void submitTopic() {
        //TODO
    }
}
