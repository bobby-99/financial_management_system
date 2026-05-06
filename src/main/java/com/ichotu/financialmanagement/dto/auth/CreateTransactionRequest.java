package com.ichotu.financialmanagement.dto.auth;



import com.ichotu.financialmanagement.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTransactionRequest {
    private Double amount;
    private String description;
    private UUID categoryId;
    private TransactionType type;
}
