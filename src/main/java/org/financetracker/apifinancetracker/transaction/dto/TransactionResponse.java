package org.financetracker.apifinancetracker.transaction.dto;

import org.financetracker.apifinancetracker.common.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private TransactionType type;
    private Long userId;
    private Long subcategoryId;

    public TransactionResponse() {}

    public TransactionResponse(Long id, BigDecimal amount, String description, LocalDateTime date, TransactionType type, Long userId, Long subcategoryId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.type = type;
        this.userId = userId;
        this.subcategoryId = subcategoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
