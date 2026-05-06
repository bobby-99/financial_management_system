package com.ichotu.financialmanagement.controller;

import com.ichotu.financialmanagement.dto.auth.CreateTransactionRequest;
import com.ichotu.financialmanagement.dto.auth.TransactionResponse;
import com.ichotu.financialmanagement.service.TransactionService;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@Data
public class TransactionController {


    private final TransactionService transactionService;

    @PostMapping
    public String create(@RequestBody CreateTransactionRequest request){
        var auth = SecurityContextHolder.getContext().getAuthentication();
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
    public List<TransactionResponse> getUserTransactions(){

        var auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = (UUID) auth.getPrincipal();

        return transactionService.getUserTransactions(userId)
                .stream()
                .map(t -> new TransactionResponse(
                        t.getId(),
                        t.getAmount(),
                        t.getDescription(),
                        t.getCreatedAt(),
                        t.getType()
                ))
                .toList();
    }

}

