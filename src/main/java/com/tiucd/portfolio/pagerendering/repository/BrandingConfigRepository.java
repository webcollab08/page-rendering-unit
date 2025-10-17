package com.tiucd.portfolio.pagerendering.repository;

import com.tiucd.portfolio.pagerendering.model.entity.BrandingConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandingConfigRepository extends JpaRepository<BrandingConfig, Long> {
    
    @Query("SELECT b FROM BrandingConfig b WHERE b.isActive = true")
    Optional<BrandingConfig> findCurrentConfig();
    
    Optional<BrandingConfig> findByConfigName(String configName);
}