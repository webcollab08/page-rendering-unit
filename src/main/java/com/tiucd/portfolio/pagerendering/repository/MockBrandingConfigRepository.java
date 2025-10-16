package com.tiucd.portfolio.pagerendering.repository;

import com.tiucd.portfolio.pagerendering.model.entity.BrandingConfig;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Mock Branding Config Repository - No Database Required
 * For demo purposes without H2 database dependency
 */
@Repository
public class MockBrandingConfigRepository {
    
    private final Map<String, BrandingConfig> mockData = new HashMap<>();
    
    public MockBrandingConfigRepository() {
        // Initialize with sample data
        BrandingConfig defaultConfig = new BrandingConfig("default-theme", 
            "/images/logo.svg", "#007bff", "#6c757d", 
            "Arial, sans-serif", "modern");
        defaultConfig.setId(1L);
        defaultConfig.setIsActive(true);
        mockData.put("default-theme", defaultConfig);
        
        BrandingConfig darkConfig = new BrandingConfig("dark-theme", 
            "/images/logo.svg", "#343a40", "#495057", 
            "Roboto, sans-serif", "dark");
        darkConfig.setId(2L);
        darkConfig.setIsActive(false);
        mockData.put("dark-theme", darkConfig);
    }
    
    public Optional<BrandingConfig> findCurrentConfig() {
        return mockData.values().stream()
            .filter(BrandingConfig::getIsActive)
            .findFirst();
    }
    
    public Optional<BrandingConfig> findByConfigName(String configName) {
        return Optional.ofNullable(mockData.get(configName));
    }
    
    public List<BrandingConfig> findByTheme(String theme) {
        return mockData.values().stream()
            .filter(config -> theme.equals(config.getThemeName()))
            .toList();
    }
    
    public List<BrandingConfig> findAllActive() {
        return new ArrayList<>(mockData.values());
    }
    
    public BrandingConfig save(BrandingConfig config) {
        if (config.getId() == null) {
            config.setId((long) (mockData.size() + 1));
        }
        mockData.put(config.getConfigName(), config);
        return config;
    }
    
    public void activateConfig(String configName) {
        // Deactivate all
        mockData.values().forEach(config -> config.setIsActive(false));
        // Activate specified
        BrandingConfig config = mockData.get(configName);
        if (config != null) {
            config.setIsActive(true);
        }
    }
}