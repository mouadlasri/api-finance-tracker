package org.financetracker.apifinancetracker.transaction.dto;

import java.math.BigDecimal;

public class CategoryTotalResponse {
    private Long userId;
    private Long categoryId;
    private BigDecimal total;

    public CategoryTotalResponse() {}

    public CategoryTotalResponse(Long userId, Long categoryId, BigDecimal total) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.total = total;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
