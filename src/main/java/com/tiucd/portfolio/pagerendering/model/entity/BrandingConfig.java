package com.tiucd.portfolio.pagerendering.model.entity;

import java.time.LocalDateTime;

/**
 * Branding Configuration Entity
 * Represents branding and theme configuration for page rendering
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Add validation for color codes (hex format)
 * - Consider caching branding configurations for performance
 * - Implement versioning for branding changes in production
 */
public class BrandingConfig {
    
    private Long id;
    private String configName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private String fontFamily;
    private String themeName;
    private String cssOverrides;
    private Boolean isActive;
    private LocalDateTime createdDate;
    
    // Default constructor
    public BrandingConfig() {}
    
    // Constructor for creating new branding configuration
    public BrandingConfig(String configName, String logoUrl, String primaryColor, 
                         String secondaryColor, String fontFamily, String themeName) {
        this.configName = configName;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.fontFamily = fontFamily;
        this.themeName = themeName;
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getConfigName() {
        return configName;
    }
    
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    
    public String getLogoUrl() {
        return logoUrl;
    }
    
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    
    public String getPrimaryColor() {
        return primaryColor;
    }
    
    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }
    
    public String getSecondaryColor() {
        return secondaryColor;
    }
    
    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }
    
    public String getFontFamily() {
        return fontFamily;
    }
    
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
    
    public String getThemeName() {
        return themeName;
    }
    
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    
    public String getCssOverrides() {
        return cssOverrides;
    }
    
    public void setCssOverrides(String cssOverrides) {
        this.cssOverrides = cssOverrides;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}