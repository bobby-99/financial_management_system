package com.ichotu.financialmanagement.dto.auth;

import lombok.Getter;

@Getter
public class CategorySummaryResponse {

    private String categoryName;
    private Double totalAmount;

    public CategorySummaryResponse(String categoryName,
                                   Double totalAmount) {
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
    }

}