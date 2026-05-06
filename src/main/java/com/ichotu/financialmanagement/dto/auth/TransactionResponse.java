package com.ichotu.financialmanagement.dto.auth;

import com.ichotu.financialmanagement.entity.TransactionType;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
public class TransactionResponse {

    private UUID id;
    private Double amount;
    private String description;
    private OffsetDateTime createdAt;
    private TransactionType type;

    public TransactionResponse(UUID id, Double amount, String description, OffsetDateTime createdAt, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.type = type;
    }
}
