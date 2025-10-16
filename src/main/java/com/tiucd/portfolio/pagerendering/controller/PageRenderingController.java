package com.tiucd.portfolio.pagerendering.controller;

import com.tiucd.portfolio.pagerendering.model.dto.PageRenderResponse;
import com.tiucd.portfolio.pagerendering.model.entity.BrandingConfig;
import com.tiucd.portfolio.pagerendering.model.entity.PageMetadata;
import com.tiucd.portfolio.pagerendering.service.BrandingService;
import com.tiucd.portfolio.pagerendering.service.PageRenderingService;
import com.tiucd.portfolio.pagerendering.service.MockPerformanceOptimizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Page Rendering Controller
 * REST API and Web UI endpoints for page rendering functionality
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Add comprehensive input validation and sanitization
 * - Implement rate limiting for API endpoints
 * - Add API versioning for backward compatibility
 * - Implement proper error handling and logging
 * - Add API documentation with OpenAPI/Swagger
 */
@Controller
@RequestMapping("/api/page")
public class PageRenderingController {
    
    private final PageRenderingService pageRenderingService;
    private final BrandingService brandingService;
    private final MockPerformanceOptimizationService performanceService;
    
    @Autowired
    public PageRenderingController(PageRenderingService pageRenderingService,
                                  BrandingService brandingService,
                                  MockPerformanceOptimizationService performanceService) {
        this.pageRenderingService = pageRenderingService;
        this.brandingService = brandingService;
        this.performanceService = performanceService;
    }
    
    /**
     * Render landing page - REST API endpoint
     * PRODUCTION MIGRATION: Add caching headers and CDN integration
     */
    @GetMapping("/render/{pageId}")
    @ResponseBody
    public ResponseEntity<PageRenderResponse> renderPage(@PathVariable String pageId) {
        try {
            PageRenderResponse response = pageRenderingService.renderLandingPage(pageId);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // PRODUCTION MIGRATION: Implement proper error logging
            PageRenderResponse errorResponse = new PageRenderResponse(pageId, "Internal server error");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    /**
     * Get page metadata - REST API endpoint
     */
    @GetMapping("/metadata/{pageId}")
    @ResponseBody
    public ResponseEntity<PageMetadata> getPageMetadata(@PathVariable String pageId) {
        PageMetadata metadata = pageRenderingService.getPageMetadata(pageId);
        
        if (metadata != null) {
            return ResponseEntity.ok(metadata);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Validate page performance - REST API endpoint
     */
    @GetMapping("/performance/{pageId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getPagePerformance(@PathVariable String pageId) {
        try {
            Map<String, Object> performance = performanceService.getPerformanceSummary(pageId);
            boolean meetssSLA = performanceService.meetsPerformanceSLA(pageId);
            String recommendations = performanceService.getPerformanceRecommendations(pageId);
            
            performance.put("meets_sla", meetssSLA);
            performance.put("recommendations", recommendations);
            
            return ResponseEntity.ok(performance);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Web UI - Landing page view
     * PRODUCTION MIGRATION: Add SEO optimization and meta tags
     */
    @GetMapping("/view/{pageId}")
    public String viewPage(@PathVariable String pageId, Model model) {
        try {
            PageMetadata metadata = pageRenderingService.getPageMetadata(pageId);
            BrandingConfig branding = brandingService.getBrandingConfig();
            
            if (metadata == null) {
                model.addAttribute("error", "Page not found: " + pageId);
                return "error";
            }
            
            model.addAttribute("pageMetadata", metadata);
            model.addAttribute("branding", branding);
            model.addAttribute("pageId", pageId);
            
            return "landing-page";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading page: " + e.getMessage());
            return "error";
        }
    }
    

    
    /**
     * Web UI - Performance analytics page
     */
    @GetMapping("/analytics/{pageId}")
    public String analytics(@PathVariable String pageId, Model model) {
        try {
            Map<String, Object> performance = performanceService.getPerformanceSummary(pageId);
            Map<String, Object> trends = performanceService.getPerformanceTrends(pageId, 30);
            String recommendations = performanceService.getPerformanceRecommendations(pageId);
            boolean meetsSLA = performanceService.meetsPerformanceSLA(pageId);
            
            model.addAttribute("pageId", pageId);
            model.addAttribute("performance", performance);
            model.addAttribute("trends", trends);
            model.addAttribute("recommendations", recommendations);
            model.addAttribute("meetsSLA", meetsSLA);
            
            return "analytics";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading analytics: " + e.getMessage());
            return "error";
        }
    }
    
    /**
     * API endpoint to activate branding configuration
     * PRODUCTION MIGRATION: Add authentication and authorization
     */
    @PostMapping("/branding/activate/{configName}")
    @ResponseBody
    public ResponseEntity<String> activateBranding(@PathVariable String configName) {
        try {
            brandingService.activateBrandingConfig(configName);
            return ResponseEntity.ok("Branding configuration activated: " + configName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error activating branding configuration");
        }
    }
    
    /**
     * Home page redirect
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/api/page/view/landing-home";
    }
}