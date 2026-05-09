package com.ichotu.financialmanagement.controller;

import com.ichotu.financialmanagement.dto.auth.CreateTransactionRequest;
import com.ichotu.financialmanagement.dto.auth.TransactionResponse;
import com.ichotu.financialmanagement.service.TransactionService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@Data
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public String create(
            @Valid @RequestBody CreateTransactionRequest request
    ) {

        var auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        UUID userId = (UUID) auth.getPrincipal();

        transactionService.create(
                request.getAmount(),
                request.getDescription(),
                request.getCategoryId(),
                request.getType(),
                userId
        );

        return "Transaction Created";
    }

    @GetMapping
    public Page<TransactionResponse> getUserTransactions(
            Pageable pageable
    ) {

        var auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        UUID userId = (UUID) auth.getPrincipal();

        return transactionService
                .getUserTransactions(userId, pageable);
    }

    @GetMapping("/filter")
    public Page<TransactionResponse> filterByDate(
            @RequestParam OffsetDateTime startDate,
            @RequestParam OffsetDateTime endDate,
            Pageable pageable
    ) {

        UUID userId = (UUID) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return transactionService.getTransactionsByDateRange(
                userId,
                startDate,
                endDate,
                pageable
        );
    }

}