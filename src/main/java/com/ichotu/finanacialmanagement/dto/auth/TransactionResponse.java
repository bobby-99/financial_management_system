package com.ichotu.finanacialmanagement.dto.auth;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
public class TransactionResponse {

    private UUID id;
    private Double amount;
    private String description;
    private OffsetDateTime createdAt;

    public TransactionResponse(UUID id, Double amount, String description, OffsetDateTime createdAt) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }
}
