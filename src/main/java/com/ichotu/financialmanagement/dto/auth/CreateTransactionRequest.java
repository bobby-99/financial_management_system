package com.ichotu.financialmanagement.dto.auth;



import com.ichotu.financialmanagement.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTransactionRequest {

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String description;

    @NotNull
    private UUID categoryId;

    @NotNull
    private TransactionType type;
}
