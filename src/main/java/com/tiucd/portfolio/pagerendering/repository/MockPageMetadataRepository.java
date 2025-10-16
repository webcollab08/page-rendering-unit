package com.tiucd.portfolio.pagerendering.repository;

import com.tiucd.portfolio.pagerendering.model.entity.PageMetadata;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Mock Page Metadata Repository - No Database Required
 * For demo purposes without H2 database dependency
 */
@Repository
public class MockPageMetadataRepository {
    
    private final Map<String, PageMetadata> mockData = new HashMap<>();
    
    public MockPageMetadataRepository() {
        // Initialize with sample data
        PageMetadata landing = new PageMetadata("landing-home", 
            "TIUCD Portfolio - Home", 
            "Welcome to TIUCD Portfolio showcasing innovative solutions", 
            "portfolio, technology, innovation, solutions", 
            "TIUCD Team");
        landing.setId(1L);
        mockData.put("landing-home", landing);
        
        PageMetadata about = new PageMetadata("about-us", 
            "About Us - TIUCD Portfolio", 
            "Learn about our company mission and values", 
            "about, company, mission, values, team", 
            "TIUCD Team");
        about.setId(2L);
        mockData.put("about-us", about);
    }
    
    public Optional<PageMetadata> findByPageId(String pageId) {
        return Optional.ofNullable(mockData.get(pageId));
    }
    
    public List<PageMetadata> findAllActive() {
        return new ArrayList<>(mockData.values());
    }
    
    public PageMetadata save(PageMetadata metadata) {
        if (metadata.getId() == null) {
            metadata.setId((long) (mockData.size() + 1));
        }
        mockData.put(metadata.getPageId(), metadata);
        return metadata;
    }
    
    public void deleteByPageId(String pageId) {
        mockData.remove(pageId);
    }
}