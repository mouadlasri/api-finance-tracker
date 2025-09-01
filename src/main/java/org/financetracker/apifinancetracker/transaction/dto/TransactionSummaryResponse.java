package org.financetracker.apifinancetracker.transaction.dto;

import java.math.BigDecimal;

public class TransactionSummaryResponse {
    private Long userId;
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;

    public TransactionSummaryResponse() {}

    public TransactionSummaryResponse(Long userId, BigDecimal totalIncome, BigDecimal totalExpenses) {
        this.userId = userId;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
