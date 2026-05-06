package com.ichotu.finanacialmanagement.service;

import com.ichotu.finanacialmanagement.entity.Transaction;
import com.ichotu.finanacialmanagement.repository.CategoryRepository;
import com.ichotu.finanacialmanagement.repository.TransactionRepository;
import lombok.Data;
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

    public Transaction create(Double amount, String Description, UUID categoryId, UUID userId){

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
        t.setCategoryId(categoryId);
        t.setCreatedAt(OffsetDateTime.now());


        return transactionRepository.save(t);
    }

}
