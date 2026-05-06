package com.ichotu.financialmanagement.service;

import com.ichotu.financialmanagement.entity.Transaction;
import com.ichotu.financialmanagement.entity.TransactionType;
import com.ichotu.financialmanagement.repository.CategoryRepository;
import com.ichotu.financialmanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Transaction> getUserTransactions(UUID userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Transaction create(Double amount, String Description, UUID categoryId, TransactionType type, UUID userId){

        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getUserId().equals(userId)){
            throw new RuntimeException("Category Does not belong to User");
        }
        Transaction t = new Transaction();

        t.setId(UUID.randomUUID());
        t.setAmount(amount);
        t.setDescription(Description);
        t.setUserId(userId);
        t.setType(type);
        t.setCategoryId(categoryId);
        t.setCreatedAt(OffsetDateTime.now());


        return transactionRepository.save(t);
    }

}
