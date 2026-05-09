package com.ichotu.financialmanagement.service;

import com.ichotu.financialmanagement.dto.auth.TransactionResponse;
import com.ichotu.financialmanagement.entity.Transaction;
import com.ichotu.financialmanagement.entity.TransactionType;
import com.ichotu.financialmanagement.repository.CategoryRepository;
import com.ichotu.financialmanagement.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            CategoryRepository categoryRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<TransactionResponse>
    getUserTransactions(UUID userId,
                        Pageable pageable) {

        return transactionRepository
                .findByUserId(userId, pageable)
                .map(this::mapToResponse);
    }

    public Page<TransactionResponse> getTransactionsByDateRange(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            Pageable pageable
    ) {

        return transactionRepository
                .findByUserIdAndCreatedAtBetween(
                        userId,
                        startDate,
                        endDate,
                        pageable
                )
                .map(this::mapToResponse);
    }

    public Transaction create(
            Double amount,
            String description,
            UUID categoryId,
            TransactionType type,
            UUID userId
    ) {

        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        if (!category.getUserId().equals(userId)) {
            throw new RuntimeException(
                    "Category does not belong to User"
            );
        }

        Transaction t = new Transaction();

        t.setId(UUID.randomUUID());
        t.setAmount(amount);
        t.setDescription(description);
        t.setUserId(userId);
        t.setType(type);
        t.setCategoryId(categoryId);
        t.setCreatedAt(OffsetDateTime.now());

        return transactionRepository.save(t);
    }

    private TransactionResponse mapToResponse(Transaction t) {

        return new TransactionResponse(
                t.getId(),
                t.getAmount(),
                t.getDescription(),
                t.getCreatedAt(),
                t.getType()
        );
    }
}