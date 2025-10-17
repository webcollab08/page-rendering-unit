package com.tiucd.portfolio.pagerendering.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "responsive_breakpoints")
public class ResponsiveBreakpoints {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "device_type", unique = true, nullable = false)
    private String deviceType;
    
    @Column(name = "min_width", nullable = false)
    private Integer minWidth;
    
    @Column(name = "max_width")
    private Integer maxWidth;
    
    @Column(name = "grid_columns")
    private Integer gridColumns;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    // Constructors
    public ResponsiveBreakpoints() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    
    public Integer getMinWidth() { return minWidth; }
    public void setMinWidth(Integer minWidth) { this.minWidth = minWidth; }
    
    public Integer getMaxWidth() { return maxWidth; }
    public void setMaxWidth(Integer maxWidth) { this.maxWidth = maxWidth; }
    
    public Integer getGridColumns() { return gridColumns; }
    public void setGridColumns(Integer gridColumns) { this.gridColumns = gridColumns; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}