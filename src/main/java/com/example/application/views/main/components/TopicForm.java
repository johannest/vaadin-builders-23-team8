package com.example.application.views.main.components;

import com.example.application.data.entity.Category;
import com.example.application.data.entity.Status;
import com.example.application.data.entity.Topic;
import com.example.application.data.service.TopicService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;


public class TopicForm extends VerticalLayout {

    private final TopicService topicService;

    private final Binder<Topic> topicBinder;

    public TopicForm(TopicService topicService) {
        addClassName("topic-form");

        this.topicService = topicService;

        topicBinder = new Binder<>(Topic.class);

        var formTitle = new H2("Choose the topic");
        var formSubtitle = new Text("What should we discuss?");

        var categorySelect = new ComboBox<Category>();
        categorySelect.setItems(Category.values());
        categorySelect.setItemLabelGenerator(Enum::name);
        topicBinder.forField(categorySelect)
                .bind(Topic::getCategory, Topic::setCategory);

        var topicTitle = new TextField("Topic");
        topicTitle.setWidth(100, Unit.PERCENTAGE);
        topicTitle.setPlaceholder("Short, descriptive title");
        topicBinder.forField(topicTitle)
                .asRequired()
                .withValidator(new StringLengthValidator("Title message cannot be longer than 80 characters",
                        1, 80))
                .bind(Topic::getTitle, Topic::setTitle);

        var topicDescription = new TextArea("Question");
        topicDescription.setWidth(100, Unit.PERCENTAGE);
        topicDescription.setPlaceholder("What seems to be the problem?");
        topicBinder.forField(topicDescription)
                .asRequired()
                .bind(Topic::getDescription, Topic::setDescription);

        var submit = new Button("Submit", click -> submitTopic());

        setAlignSelf(Alignment.END, submit);

        var form = new FormLayout(categorySelect, topicTitle, topicDescription);

        add(formTitle, formSubtitle, form, submit);

        topicBinder.setBean(new Topic());
    }

    private void submitTopic() {
        if (topicBinder.validate().isOk()) {
            final Topic topic = topicBinder.getBean();
            topic.setAnonymous(true);
            topic.setStatus(Status.NEW);
//            topic.setSubmitter(getVaadiner); TODO

            topicService.save(topic);
            topicBinder.setBean(new Topic());
            ComponentUtil.fireEvent(UI.getCurrent(), new CreateTopicEvent(this));
        }

    }

}
