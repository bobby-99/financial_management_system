package com.ichotu.financialmanagement.controller;


import com.ichotu.financialmanagement.dto.auth.AnalyticsSummaryResponse;
import com.ichotu.financialmanagement.dto.auth.CategorySummaryResponse;
import com.ichotu.financialmanagement.service.AnalyticsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public AnalyticsSummaryResponse getSummary() {
        UUID userId = (UUID)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return analyticsService.getSummary(userId);
    }

    @GetMapping("/categories")
    public List<CategorySummaryResponse> getCategorySummary() {
        UUID userId = (UUID)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return analyticsService.getCategorySummary(userId);
    }
;
}
