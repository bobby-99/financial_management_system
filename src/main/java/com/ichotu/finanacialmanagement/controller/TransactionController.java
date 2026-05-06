package com.ichotu.finanacialmanagement.controller;

import com.ichotu.finanacialmanagement.dto.auth.CreateTransactionRequest;
import com.ichotu.finanacialmanagement.dto.auth.TransactionResponse;
import com.ichotu.finanacialmanagement.service.TransactionService;
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
                request.getCategoryID(),
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
                        t.getCreatedAt()
                ))
                .toList();
    }

}

