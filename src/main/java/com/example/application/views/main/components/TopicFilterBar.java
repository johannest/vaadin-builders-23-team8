package com.example.application.views.main.components;

import com.example.application.data.entity.Category;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.Optional;

public class TopicFilterBar extends HorizontalLayout {
    private TextField searchField;
    private ComboBox<Category> categorySelect;
    private SearchListener searchListener;

    public interface SearchListener {
        void searchValueChanged(Optional<String> searchTerm, Optional<Category> category);
    }

    public TopicFilterBar() {
        searchField = new TextField();
        searchField.setWidth("100%");
        searchField.setPlaceholder("Search topics...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.TIMEOUT);
        searchField.addValueChangeListener(e -> {
            if (searchListener != null) {
                searchListener.searchValueChanged(getSearchTerm(), getCategory());
            }
        });

        categorySelect = new ComboBox<>();
        categorySelect.setPlaceholder("Category");
        categorySelect.setItems(Category.values());
        categorySelect.setClearButtonVisible(true);
        categorySelect.addValueChangeListener(e -> {
            if (searchListener != null) {
                searchListener.searchValueChanged(getSearchTerm(), getCategory());
            }
        });

        add(searchField, categorySelect);
        expand(searchField);
    }

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    private Optional<String> getSearchTerm() {
        String searchTerm = searchField.getValue();
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            return Optional.of(searchTerm.trim());
        } else {
            return Optional.empty();
        }
    }

    private Optional<Category> getCategory() {
        Category category = categorySelect.getValue();
        if (category != null) {
            return Optional.of(category);
        }
        return Optional.empty();
    }
}
