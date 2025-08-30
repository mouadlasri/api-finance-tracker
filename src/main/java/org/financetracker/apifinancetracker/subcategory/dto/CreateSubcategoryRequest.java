package org.financetracker.apifinancetracker.subcategory.dto;

public class CreateSubcategoryRequest {
    private String name;

    public CreateSubcategoryRequest() {}

    public CreateSubcategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
