package com.tiucd.portfolio.pagerendering.service;

import com.tiucd.portfolio.pagerendering.model.entity.BrandingConfig;
import com.tiucd.portfolio.pagerendering.repository.MockBrandingConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Branding Service
 * Business logic for branding configuration management
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Implement branding cache with TTL for performance
 * - Add branding validation rules (color format, URL validation)
 * - Consider A/B testing for different branding configurations
 */
@Service
public class BrandingService {
    
    private final MockBrandingConfigRepository brandingConfigRepository;
    
    @Autowired
    public BrandingService(MockBrandingConfigRepository brandingConfigRepository) {
        this.brandingConfigRepository = brandingConfigRepository;
    }
    
    /**
     * Get current active branding configuration
     * PRODUCTION MIGRATION: Cache this result with Redis/Hazelcast
     */
    public BrandingConfig getBrandingConfig() {
        Optional<BrandingConfig> config = brandingConfigRepository.findCurrentConfig();
        return config.orElse(createDefaultBranding());
    }
    
    /**
     * Get branding configuration by name
     */
    public BrandingConfig getBrandingConfig(String configName) {
        Optional<BrandingConfig> config = brandingConfigRepository.findByConfigName(configName);
        return config.orElse(null);
    }
    
    /**
     * Apply branding to template content
     * PRODUCTION MIGRATION: Use template engines for complex branding application
     */
    public String applyBranding(String template, BrandingConfig branding) {
        if (branding == null) {
            branding = createDefaultBranding();
        }
        
        // Replace branding placeholders in template
        String brandedTemplate = template
            .replace("{{LOGO_URL}}", branding.getLogoUrl() != null ? branding.getLogoUrl() : "")
            .replace("{{PRIMARY_COLOR}}", branding.getPrimaryColor())
            .replace("{{SECONDARY_COLOR}}", branding.getSecondaryColor())
            .replace("{{FONT_FAMILY}}", branding.getFontFamily())
            .replace("{{THEME_NAME}}", branding.getThemeName())
            .replace("{{CSS_OVERRIDES}}", branding.getCssOverrides() != null ? branding.getCssOverrides() : "");
        
        return brandedTemplate;
    }
    
    /**
     * Validate branding compliance
     * PRODUCTION MIGRATION: Implement comprehensive branding guidelines validation
     */
    public boolean validateBrandCompliance(BrandingConfig branding) {
        if (branding == null) {
            return false;
        }
        
        // Basic validation rules
        if (branding.getConfigName() == null || branding.getConfigName().trim().isEmpty()) {
            return false;
        }
        
        if (branding.getPrimaryColor() == null || !isValidHexColor(branding.getPrimaryColor())) {
            return false;
        }
        
        if (branding.getSecondaryColor() == null || !isValidHexColor(branding.getSecondaryColor())) {
            return false;
        }
        
        if (branding.getFontFamily() == null || branding.getFontFamily().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Get all available branding configurations
     */
    public List<BrandingConfig> getAllBrandingConfigs() {
        return brandingConfigRepository.findAllActive();
    }
    
    /**
     * Save branding configuration
     * PRODUCTION MIGRATION: Add validation and audit logging
     */
    public BrandingConfig saveBrandingConfig(BrandingConfig branding) {
        if (!validateBrandCompliance(branding)) {
            throw new IllegalArgumentException("Branding configuration does not meet compliance requirements");
        }
        
        return brandingConfigRepository.save(branding);
    }
    
    /**
     * Activate branding configuration
     * PRODUCTION MIGRATION: Add change management and rollback capabilities
     */
    public void activateBrandingConfig(String configName) {
        BrandingConfig config = getBrandingConfig(configName);
        if (config == null) {
            throw new IllegalArgumentException("Branding configuration not found: " + configName);
        }
        
        brandingConfigRepository.activateConfig(configName);
    }
    
    /**
     * Get branding configurations by theme
     */
    public List<BrandingConfig> getBrandingByTheme(String theme) {
        return brandingConfigRepository.findByTheme(theme);
    }
    
    /**
     * Create default branding configuration
     */
    private BrandingConfig createDefaultBranding() {
        BrandingConfig defaultBranding = new BrandingConfig();
        defaultBranding.setConfigName("default");
        defaultBranding.setLogoUrl("/static/images/default-logo.png");
        defaultBranding.setPrimaryColor("#007bff");
        defaultBranding.setSecondaryColor("#6c757d");
        defaultBranding.setFontFamily("Arial, sans-serif");
        defaultBranding.setThemeName("default");
        defaultBranding.setIsActive(true);
        
        return defaultBranding;
    }
    
    /**
     * Validate hex color format
     * PRODUCTION MIGRATION: Use comprehensive color validation library
     */
    private boolean isValidHexColor(String color) {
        if (color == null) {
            return false;
        }
        
        // Check if it matches hex color pattern (#RRGGBB or #RGB)
        return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }
}