package com.tiucd.portfolio.pagerendering.model.entity;

import java.time.LocalDateTime;

/**
 * Page Metadata Entity
 * Represents page metadata information for SEO and page configuration
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Consider adding JPA annotations for ORM mapping in production
 * - Add validation annotations for data integrity
 * - Implement audit fields (created_by, updated_by) for production
 */
public class PageMetadata {
    
    private Long id;
    private String pageId;
    private String title;
    private String description;
    private String keywords;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean isActive;
    
    // Default constructor
    public PageMetadata() {}
    
    // Constructor for creating new page metadata
    public PageMetadata(String pageId, String title, String description, String keywords, String author) {
        this.pageId = pageId;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.author = author;
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    @Override
    public String toString() {
        return "PageMetadata{" +
                "id=" + id +
                ", pageId='" + pageId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}