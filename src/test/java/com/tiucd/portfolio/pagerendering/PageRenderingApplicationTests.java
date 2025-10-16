package com.tiucd.portfolio.pagerendering;

import com.tiucd.portfolio.pagerendering.service.PageRenderingService;
import com.tiucd.portfolio.pagerendering.service.BrandingService;
import com.tiucd.portfolio.pagerendering.model.dto.PageRenderResponse;
import com.tiucd.portfolio.pagerendering.model.entity.BrandingConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Tests for Page Rendering Application
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Add comprehensive test coverage for all components
 * - Implement performance testing with JMeter/Gatling
 * - Add security testing for input validation
 * - Implement contract testing for API endpoints
 * - Add database migration testing
 */
@SpringBootTest
@ActiveProfiles("test") // PRODUCTION MIGRATION: Use separate test profile
class PageRenderingApplicationTests {

    @Autowired
    private PageRenderingService pageRenderingService;
    
    @Autowired
    private BrandingService brandingService;

    /**
     * Test application context loads successfully
     */
    @Test
    void contextLoads() {
        assertNotNull(pageRenderingService);
        assertNotNull(brandingService);
    }
    
    /**
     * Test page rendering functionality
     */
    @Test
    void testPageRendering() {
        // Test rendering existing page
        PageRenderResponse response = pageRenderingService.renderLandingPage("landing-home");
        
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("landing-home", response.getPageId());
        assertNotNull(response.getTitle());
        assertNotNull(response.getContent());
    }
    
    /**
     * Test page rendering with non-existent page
     */
    @Test
    void testPageRenderingNotFound() {
        PageRenderResponse response = pageRenderingService.renderLandingPage("non-existent-page");
        
        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals("non-existent-page", response.getPageId());
    }
    
    /**
     * Test branding service functionality
     */
    @Test
    void testBrandingService() {
        BrandingConfig branding = brandingService.getBrandingConfig();
        
        assertNotNull(branding);
        assertNotNull(branding.getConfigName());
        assertNotNull(branding.getPrimaryColor());
        assertNotNull(branding.getThemeName());
    }
    
    /**
     * Test branding validation
     */
    @Test
    void testBrandingValidation() {
        BrandingConfig validBranding = new BrandingConfig();
        validBranding.setConfigName("test-config");
        validBranding.setPrimaryColor("#007bff");
        validBranding.setSecondaryColor("#6c757d");
        validBranding.setFontFamily("Arial, sans-serif");
        validBranding.setThemeName("test");
        
        assertTrue(brandingService.validateBrandCompliance(validBranding));
        
        // Test invalid branding
        BrandingConfig invalidBranding = new BrandingConfig();
        invalidBranding.setConfigName(""); // Invalid empty name
        
        assertFalse(brandingService.validateBrandCompliance(invalidBranding));
    }
}