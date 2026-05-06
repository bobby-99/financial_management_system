package com.ichotu.finanacialmanagement.dto.auth;



import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateTransactionRequest {
    private Double amount;
    private String description;
    private UUID categoryID;
}
