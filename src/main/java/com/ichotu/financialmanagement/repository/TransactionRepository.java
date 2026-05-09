package com.ichotu.financialmanagement.repository;

import com.ichotu.financialmanagement.entity.Transaction;
import com.ichotu.financialmanagement.entity.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ichotu.financialmanagement.dto.auth.CategorySummaryResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository
        extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findByUserId(UUID userId, Pageable pageable);

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM Transaction t
        WHERE t.userId = :userId
        AND t.type = :type
    """)
    Double sumByUserIdAndType(UUID userId,
                              TransactionType type);

    @Query("""
    SELECT new com.ichotu.financialmanagement.dto.auth.CategorySummaryResponse(c.name, sum(t.amount) )
    FROM Transaction t
    JOIN Category c
        ON t.categoryId = c.id
    WHERE t.userId = :userId
    AND t.type = 'EXPENSE'
    GROUP BY c.name
""")
    List<CategorySummaryResponse>
    getExpenseSummaryByCategory(UUID userId);

    Page<Transaction> findByUserIdAndCreatedAtBetween(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            Pageable pageable
    );

}