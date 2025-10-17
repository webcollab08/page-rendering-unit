package com.tiucd.portfolio.pagerendering.repository;

import com.tiucd.portfolio.pagerendering.model.entity.ResponsiveBreakpoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsiveBreakpointsRepository extends JpaRepository<ResponsiveBreakpoints, Long> {
    
    @Query("SELECT r FROM ResponsiveBreakpoints r WHERE r.isActive = true")
    List<ResponsiveBreakpoints> findAllActive();
    
    Optional<ResponsiveBreakpoints> findByDeviceType(String deviceType);
}