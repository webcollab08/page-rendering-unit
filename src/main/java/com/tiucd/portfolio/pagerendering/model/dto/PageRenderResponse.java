package com.tiucd.portfolio.pagerendering.model.dto;

/**
 * Page Render Response DTO
 * Data Transfer Object for page rendering responses
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Add JSON serialization annotations for API responses
 * - Consider adding response caching headers
 * - Implement response compression for large content
 */
public class PageRenderResponse {
    
    private String pageId;
    private String title;
    private String content;
    private BrandingInfo branding;
    private PerformanceInfo performance;
    private boolean success;
    private String message;
    
    // Default constructor
    public PageRenderResponse() {}
    
    // Constructor for successful response
    public PageRenderResponse(String pageId, String title, String content, 
                             BrandingInfo branding, PerformanceInfo performance) {
        this.pageId = pageId;
        this.title = title;
        this.content = content;
        this.branding = branding;
        this.performance = performance;
        this.success = true;
        this.message = "Page rendered successfully";
    }
    
    // Constructor for error response
    public PageRenderResponse(String pageId, String message) {
        this.pageId = pageId;
        this.success = false;
        this.message = message;
    }
    
    // Getters and Setters
    public String getPageId() {
        return pageId;
    }
    
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public BrandingInfo getBranding() {
        return branding;
    }
    
    public void setBranding(BrandingInfo branding) {
        this.branding = branding;
    }
    
    public PerformanceInfo getPerformance() {
        return performance;
    }
    
    public void setPerformance(PerformanceInfo performance) {
        this.performance = performance;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    // Nested classes for structured response data
    public static class BrandingInfo {
        private String logoUrl;
        private String primaryColor;
        private String themeName;
        
        public BrandingInfo(String logoUrl, String primaryColor, String themeName) {
            this.logoUrl = logoUrl;
            this.primaryColor = primaryColor;
            this.themeName = themeName;
        }
        
        // Getters and Setters
        public String getLogoUrl() { return logoUrl; }
        public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
        public String getPrimaryColor() { return primaryColor; }
        public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }
        public String getThemeName() { return themeName; }
        public void setThemeName(String themeName) { this.themeName = themeName; }
    }
    
    public static class PerformanceInfo {
        private long renderTimeMs;
        private int resourceCount;
        
        public PerformanceInfo(long renderTimeMs, int resourceCount) {
            this.renderTimeMs = renderTimeMs;
            this.resourceCount = resourceCount;
        }
        
        // Getters and Setters
        public long getRenderTimeMs() { return renderTimeMs; }
        public void setRenderTimeMs(long renderTimeMs) { this.renderTimeMs = renderTimeMs; }
        public int getResourceCount() { return resourceCount; }
        public void setResourceCount(int resourceCount) { this.resourceCount = resourceCount; }
    }
}