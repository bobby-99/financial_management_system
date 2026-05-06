package com.ichotu.financialmanagement.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="categories")
@Getter
@Setter
public class Category {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name="user_id", nullable = false)
    private UUID userId;

}
