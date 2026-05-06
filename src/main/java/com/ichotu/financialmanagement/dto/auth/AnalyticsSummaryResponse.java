package com.ichotu.financialmanagement.dto.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyticsSummaryResponse {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;

    public AnalyticsSummaryResponse(Double totalIncome, Double totalExpense, Double balance) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
    }
}
