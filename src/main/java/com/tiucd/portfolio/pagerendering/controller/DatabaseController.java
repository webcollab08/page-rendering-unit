package com.tiucd.portfolio.pagerendering.controller;

import com.tiucd.portfolio.pagerendering.model.entity.*;
import com.tiucd.portfolio.pagerendering.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {
    
    @Autowired
    private DatabaseService databaseService;
    
    @GetMapping("/page-metadata")
    public ResponseEntity<List<PageMetadata>> getAllPageMetadata() {
        return ResponseEntity.ok(databaseService.getAllPageMetadata());
    }
    
    @GetMapping("/page-metadata/{pageId}")
    public ResponseEntity<PageMetadata> getPageMetadata(@PathVariable String pageId) {
        PageMetadata metadata = databaseService.getPageMetadata(pageId);
        return metadata != null ? ResponseEntity.ok(metadata) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/branding-config")
    public ResponseEntity<List<BrandingConfig>> getAllBrandingConfigs() {
        return ResponseEntity.ok(databaseService.getAllBrandingConfigs());
    }
    
    @GetMapping("/branding-config/active")
    public ResponseEntity<BrandingConfig> getActiveBrandingConfig() {
        BrandingConfig config = databaseService.getActiveBrandingConfig();
        return config != null ? ResponseEntity.ok(config) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/performance-metrics")
    public ResponseEntity<List<PerformanceMetrics>> getAllPerformanceMetrics() {
        return ResponseEntity.ok(databaseService.getAllPerformanceMetrics());
    }
    
    @GetMapping("/performance-metrics/{pageId}")
    public ResponseEntity<List<PerformanceMetrics>> getPerformanceMetrics(@PathVariable String pageId) {
        return ResponseEntity.ok(databaseService.getPerformanceMetrics(pageId));
    }
    
    @GetMapping("/responsive-breakpoints")
    public ResponseEntity<List<ResponsiveBreakpoints>> getAllResponsiveBreakpoints() {
        return ResponseEntity.ok(databaseService.getAllResponsiveBreakpoints());
    }
    
    @GetMapping("/responsive-breakpoints/active")
    public ResponseEntity<List<ResponsiveBreakpoints>> getActiveBreakpoints() {
        return ResponseEntity.ok(databaseService.getActiveBreakpoints());
    }
}