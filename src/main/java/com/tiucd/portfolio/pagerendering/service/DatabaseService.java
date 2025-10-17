package com.tiucd.portfolio.pagerendering.service;

import com.tiucd.portfolio.pagerendering.model.entity.*;
import com.tiucd.portfolio.pagerendering.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    
    @Autowired
    private PageMetadataRepository pageMetadataRepository;
    
    @Autowired
    private BrandingConfigRepository brandingConfigRepository;
    
    @Autowired
    private PerformanceMetricsRepository performanceMetricsRepository;
    
    @Autowired
    private ResponsiveBreakpointsRepository responsiveBreakpointsRepository;
    
    // Page Metadata operations
    public List<PageMetadata> getAllPageMetadata() {
        return pageMetadataRepository.findAll();
    }
    
    public PageMetadata getPageMetadata(String pageId) {
        return pageMetadataRepository.findByPageId(pageId).orElse(null);
    }
    
    // Branding Config operations
    public List<BrandingConfig> getAllBrandingConfigs() {
        return brandingConfigRepository.findAll();
    }
    
    public BrandingConfig getActiveBrandingConfig() {
        return brandingConfigRepository.findCurrentConfig().orElse(null);
    }
    
    // Performance Metrics operations
    public List<PerformanceMetrics> getAllPerformanceMetrics() {
        return performanceMetricsRepository.findAll();
    }
    
    public List<PerformanceMetrics> getPerformanceMetrics(String pageId) {
        return performanceMetricsRepository.findByPageId(pageId);
    }
    
    public Double getAverageLoadTime(String pageId) {
        return performanceMetricsRepository.getAverageLoadTime(pageId);
    }
    
    // Responsive Breakpoints operations
    public List<ResponsiveBreakpoints> getAllResponsiveBreakpoints() {
        return responsiveBreakpointsRepository.findAll();
    }
    
    public List<ResponsiveBreakpoints> getActiveBreakpoints() {
        return responsiveBreakpointsRepository.findAllActive();
    }
}