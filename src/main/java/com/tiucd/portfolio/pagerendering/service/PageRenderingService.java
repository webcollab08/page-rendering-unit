package com.tiucd.portfolio.pagerendering.service;

import com.tiucd.portfolio.pagerendering.model.dto.PageRenderResponse;
import com.tiucd.portfolio.pagerendering.model.entity.BrandingConfig;
import com.tiucd.portfolio.pagerendering.model.entity.PageMetadata;
import com.tiucd.portfolio.pagerendering.repository.MockBrandingConfigRepository;
import com.tiucd.portfolio.pagerendering.repository.MockPageMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Page Rendering Service
 * Core business logic for page rendering operations
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Add caching layer (Redis/Hazelcast) for rendered pages
 * - Implement CDN integration for static assets
 * - Add performance monitoring and metrics collection
 * - Consider implementing page pre-rendering for better performance
 */
@Service
public class PageRenderingService {
    
    private final MockPageMetadataRepository pageMetadataRepository;
    private final MockBrandingConfigRepository brandingConfigRepository;
    private final MockPerformanceOptimizationService performanceService;
    
    @Autowired
    public PageRenderingService(MockPageMetadataRepository pageMetadataRepository,
                               MockBrandingConfigRepository brandingConfigRepository,
                               MockPerformanceOptimizationService performanceService) {
        this.pageMetadataRepository = pageMetadataRepository;
        this.brandingConfigRepository = brandingConfigRepository;
        this.performanceService = performanceService;
    }
    
    /**
     * Render landing page with branding and performance optimization
     * PRODUCTION MIGRATION: Add page caching and CDN integration
     */
    public PageRenderResponse renderLandingPage(String pageId) {
        long startTime = System.currentTimeMillis();
        
        try {
            // Get page metadata
            Optional<PageMetadata> metadataOpt = pageMetadataRepository.findByPageId(pageId);
            if (metadataOpt.isEmpty()) {
                return new PageRenderResponse(pageId, "Page not found");
            }
            
            PageMetadata metadata = metadataOpt.get();
            
            // Get branding configuration
            Optional<BrandingConfig> brandingOpt = brandingConfigRepository.findCurrentConfig();
            BrandingConfig branding = brandingOpt.orElse(getDefaultBranding());
            
            // Apply branding to page content
            String content = generatePageContent(metadata, branding);
            
            // Calculate performance metrics
            long renderTime = System.currentTimeMillis() - startTime;
            int resourceCount = calculateResourceCount(content);
            
            // Create branding info for response
            PageRenderResponse.BrandingInfo brandingInfo = new PageRenderResponse.BrandingInfo(
                branding.getLogoUrl(),
                branding.getPrimaryColor(),
                branding.getThemeName()
            );
            
            // Create performance info for response
            PageRenderResponse.PerformanceInfo performanceInfo = new PageRenderResponse.PerformanceInfo(
                renderTime,
                resourceCount
            );
            
            // Record performance metrics
            performanceService.recordPageLoad(pageId, renderTime, resourceCount);
            
            return new PageRenderResponse(pageId, metadata.getTitle(), content, brandingInfo, performanceInfo);
            
        } catch (Exception e) {
            // PRODUCTION MIGRATION: Implement proper error logging and monitoring
            return new PageRenderResponse(pageId, "Error rendering page: " + e.getMessage());
        }
    }
    
    /**
     * Get page metadata for SEO and configuration
     */
    public PageMetadata getPageMetadata(String pageId) {
        return pageMetadataRepository.findByPageId(pageId).orElse(null);
    }
    
    /**
     * Validate page load performance
     * PRODUCTION MIGRATION: Integrate with APM tools (New Relic, AppDynamics)
     */
    public PageRenderResponse.PerformanceInfo validatePageLoad(String pageId) {
        // Simulate performance validation
        long renderTime = performanceService.getAverageRenderTime(pageId);
        int resourceCount = performanceService.getResourceCount(pageId);
        
        return new PageRenderResponse.PerformanceInfo(renderTime, resourceCount);
    }
    
    /**
     * Generate page content with branding applied
     * PRODUCTION MIGRATION: Use template engines (Thymeleaf, Freemarker) for complex content
     */
    private String generatePageContent(PageMetadata metadata, BrandingConfig branding) {
        StringBuilder content = new StringBuilder();
        
        // Basic HTML structure with branding
        content.append("<!DOCTYPE html>");
        content.append("<html lang='en'>");
        content.append("<head>");
        content.append("<meta charset='UTF-8'>");
        content.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        content.append("<title>").append(metadata.getTitle()).append("</title>");
        content.append("<meta name='description' content='").append(metadata.getDescription()).append("'>");
        content.append("<meta name='keywords' content='").append(metadata.getKeywords()).append("'>");
        content.append("<meta name='author' content='").append(metadata.getAuthor()).append("'>");
        
        // Apply branding styles
        content.append("<style>");
        content.append(":root { --primary-color: ").append(branding.getPrimaryColor()).append("; }");
        content.append("body { font-family: ").append(branding.getFontFamily()).append("; }");
        if (branding.getCssOverrides() != null) {
            content.append(branding.getCssOverrides());
        }
        content.append("</style>");
        
        content.append("</head>");
        content.append("<body>");
        
        // Header with branding
        content.append("<header>");
        if (branding.getLogoUrl() != null) {
            content.append("<img src='").append(branding.getLogoUrl()).append("' alt='Logo' class='logo'>");
        }
        content.append("<h1>").append(metadata.getTitle()).append("</h1>");
        content.append("</header>");
        
        // Main content
        content.append("<main>");
        content.append("<p>").append(metadata.getDescription()).append("</p>");
        content.append("</main>");
        
        content.append("</body>");
        content.append("</html>");
        
        return content.toString();
    }
    
    /**
     * Calculate resource count for performance metrics
     */
    private int calculateResourceCount(String content) {
        // Simple calculation based on content analysis
        int cssCount = content.split("<style>").length - 1;
        int jsCount = content.split("<script>").length - 1;
        int imgCount = content.split("<img").length - 1;
        
        return cssCount + jsCount + imgCount + 1; // +1 for HTML itself
    }
    
    /**
     * Get default branding configuration
     */
    private BrandingConfig getDefaultBranding() {
        BrandingConfig defaultBranding = new BrandingConfig();
        defaultBranding.setConfigName("default");
        defaultBranding.setLogoUrl("/static/images/default-logo.png");
        defaultBranding.setPrimaryColor("#007bff");
        defaultBranding.setSecondaryColor("#6c757d");
        defaultBranding.setFontFamily("Arial, sans-serif");
        defaultBranding.setThemeName("default");
        return defaultBranding;
    }
}