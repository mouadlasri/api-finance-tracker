package org.financetracker.apifinancetracker.category.dto;

public class CreateCategoryRequest {
    private String name;
    private Long userId;

    public CreateCategoryRequest() {}

    public CreateCategoryRequest(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
