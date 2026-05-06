package com.ichotu.finanacialmanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Table(name="transactions")
@Data
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

    // getters/setters

}
