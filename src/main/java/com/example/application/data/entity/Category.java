package com.example.application.data.entity;

public enum Category {
    PRODUCTS("Products"), SERVICES("Services"), OPERATIONS("Operations"), HR("HR"), FINANCES("Finances");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
