package com.ichotu.financialmanagement.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Table(name="transactions")
@Getter
@Setter
public class Transaction {

    @Id
    private UUID id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(name="category_id")
    private UUID categoryId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    // getters/setters

}
