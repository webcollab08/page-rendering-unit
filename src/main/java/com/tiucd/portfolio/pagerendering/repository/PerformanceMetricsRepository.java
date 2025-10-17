package com.tiucd.portfolio.pagerendering.repository;

import com.tiucd.portfolio.pagerendering.model.entity.PerformanceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceMetricsRepository extends JpaRepository<PerformanceMetrics, Long> {
    
    List<PerformanceMetrics> findByPageId(String pageId);
    
    @Query("SELECT p FROM PerformanceMetrics p WHERE p.pageId = :pageId ORDER BY p.recordedDate DESC")
    List<PerformanceMetrics> findByPageIdOrderByRecordedDateDesc(@Param("pageId") String pageId);
    
    @Query("SELECT AVG(p.loadTimeMs) FROM PerformanceMetrics p WHERE p.pageId = :pageId")
    Double getAverageLoadTime(@Param("pageId") String pageId);
}