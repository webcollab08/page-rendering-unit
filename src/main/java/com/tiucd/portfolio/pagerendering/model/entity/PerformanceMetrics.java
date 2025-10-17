package com.tiucd.portfolio.pagerendering.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_metrics")
public class PerformanceMetrics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "page_id", nullable = false)
    private String pageId;
    
    @Column(name = "load_time_ms", nullable = false)
    private Integer loadTimeMs;
    
    @Column(name = "render_time_ms")
    private Integer renderTimeMs;
    
    @Column(name = "resource_count")
    private Integer resourceCount;
    
    @Column(name = "page_size_kb")
    private Integer pageSizeKb;
    
    @Column(name = "user_agent")
    private String userAgent;
    
    @Column(name = "device_type")
    private String deviceType;
    
    @Column(name = "recorded_date")
    private LocalDateTime recordedDate;
    
    // Constructors
    public PerformanceMetrics() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPageId() { return pageId; }
    public void setPageId(String pageId) { this.pageId = pageId; }
    
    public Integer getLoadTimeMs() { return loadTimeMs; }
    public void setLoadTimeMs(Integer loadTimeMs) { this.loadTimeMs = loadTimeMs; }
    
    public Integer getRenderTimeMs() { return renderTimeMs; }
    public void setRenderTimeMs(Integer renderTimeMs) { this.renderTimeMs = renderTimeMs; }
    
    public Integer getResourceCount() { return resourceCount; }
    public void setResourceCount(Integer resourceCount) { this.resourceCount = resourceCount; }
    
    public Integer getPageSizeKb() { return pageSizeKb; }
    public void setPageSizeKb(Integer pageSizeKb) { this.pageSizeKb = pageSizeKb; }
    
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    
    public LocalDateTime getRecordedDate() { return recordedDate; }
    public void setRecordedDate(LocalDateTime recordedDate) { this.recordedDate = recordedDate; }
}