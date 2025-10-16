package com.tiucd.portfolio.pagerendering.service;

import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Mock Performance Optimization Service - No Database Required
 * For demo purposes without database dependency
 */
@Service
public class MockPerformanceOptimizationService {
    
    public void recordPageLoad(String pageId, long loadTimeMs, int resourceCount) {
        // Mock implementation - just log
        System.out.println("Performance recorded: " + pageId + " - " + loadTimeMs + "ms");
    }
    
    public long getAverageRenderTime(String pageId) {
        return 850L; // Mock average render time
    }
    
    public int getResourceCount(String pageId) {
        return 12; // Mock resource count
    }
    
    public Map<String, Object> getPerformanceSummary(String pageId) {
        return Map.of(
            "avg_load_time", 1200L,
            "avg_render_time", 850L,
            "avg_resource_count", 12,
            "avg_page_size", 245,
            "total_requests", 150L
        );
    }
    
    public boolean meetsPerformanceSLA(String pageId) {
        return true; // Mock SLA compliance
    }
    
    public String getPerformanceRecommendations(String pageId) {
        return "• Page performance is within acceptable limits. No immediate optimizations needed.";
    }
    
    public Map<String, Object> getPerformanceTrends(String pageId, int days) {
        return Map.of("trends", java.util.Collections.emptyList());
    }
}