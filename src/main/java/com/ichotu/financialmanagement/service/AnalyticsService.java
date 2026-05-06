package com.ichotu.financialmanagement.service;

import com.ichotu.financialmanagement.dto.auth.AnalyticsSummaryResponse;
import com.ichotu.financialmanagement.dto.auth.CategorySummaryResponse;
import com.ichotu.financialmanagement.entity.TransactionType;
import com.ichotu.financialmanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnalyticsService {

    private final TransactionRepository transactionRepository;


    public AnalyticsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<CategorySummaryResponse> getCategorySummary(UUID userId) {
        return transactionRepository.getExpenseSummaryByCategory(userId);
    }

    public AnalyticsSummaryResponse getSummary(UUID userId) {

        Double income = transactionRepository.sumByUserIdAndType(userId, TransactionType.INCOME);

        Double expense = transactionRepository.sumByUserIdAndType(userId, TransactionType.EXPENSE);

        Double balance = income - expense;

        return new AnalyticsSummaryResponse(
                income,
                expense,
                balance
        );

    }
}
