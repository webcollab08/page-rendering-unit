package com.tiucd.portfolio.pagerendering.repository;

import com.tiucd.portfolio.pagerendering.model.entity.PageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageMetadataRepository extends JpaRepository<PageMetadata, Long> {
    
    Optional<PageMetadata> findByPageId(String pageId);
    
    @Query("SELECT p FROM PageMetadata p WHERE p.isActive = 1")
    List<PageMetadata> findAllActive();
}