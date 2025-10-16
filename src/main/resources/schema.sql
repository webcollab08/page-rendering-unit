-- H2 Database Schema for Page Rendering Unit
-- PRODUCTION MIGRATION: Modify data types and constraints for Oracle/PostgreSQL/MySQL

-- Page Metadata Table
-- PRODUCTION MIGRATION: 
-- Oracle: Use VARCHAR2 instead of VARCHAR, NUMBER instead of BIGINT
-- PostgreSQL: Use SERIAL for auto-increment, TEXT for large content
-- MySQL: Use AUTO_INCREMENT for id, TEXT for large content
CREATE TABLE IF NOT EXISTS page_metadata (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    page_id VARCHAR(100) NOT NULL UNIQUE,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    keywords VARCHAR(500),
    author VARCHAR(100),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
    -- PRODUCTION MIGRATION: Oracle uses NUMBER(1) for BOOLEAN, PostgreSQL/MySQL support BOOLEAN
);

-- Branding Configuration Table
-- PRODUCTION MIGRATION: Adjust CLOB/TEXT data types based on database
CREATE TABLE IF NOT EXISTS branding_config (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    config_name VARCHAR(100) NOT NULL UNIQUE,
    logo_url VARCHAR(500),
    primary_color VARCHAR(7), -- Hex color code
    secondary_color VARCHAR(7),
    font_family VARCHAR(100),
    theme_name VARCHAR(50),
    css_overrides TEXT, -- PRODUCTION MIGRATION: Use CLOB for Oracle, TEXT for PostgreSQL/MySQL
    is_active BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Performance Metrics Table
-- PRODUCTION MIGRATION: Consider partitioning for large datasets in production
CREATE TABLE IF NOT EXISTS performance_metrics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    page_id VARCHAR(100) NOT NULL,
    load_time_ms INTEGER NOT NULL,
    render_time_ms INTEGER,
    resource_count INTEGER,
    page_size_kb INTEGER,
    user_agent VARCHAR(500),
    device_type VARCHAR(50), -- mobile, tablet, desktop
    recorded_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- PRODUCTION MIGRATION: Add indexes for performance queries
    INDEX idx_page_id (page_id),
    INDEX idx_recorded_date (recorded_date)
    -- PRODUCTION MIGRATION: Oracle syntax: CREATE INDEX idx_name ON table(column)
);

-- Responsive Breakpoints Configuration
-- PRODUCTION MIGRATION: Simple table structure, minimal changes needed
CREATE TABLE IF NOT EXISTS responsive_breakpoints (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    device_type VARCHAR(50) NOT NULL UNIQUE,
    min_width INTEGER NOT NULL,
    max_width INTEGER,
    grid_columns INTEGER DEFAULT 12,
    is_active BOOLEAN DEFAULT TRUE
);

-- PRODUCTION MIGRATION NOTES:
-- 1. Oracle: Replace AUTO_INCREMENT with SEQUENCE and TRIGGER
-- 2. PostgreSQL: Replace AUTO_INCREMENT with SERIAL or IDENTITY
-- 3. MySQL: AUTO_INCREMENT works as-is
-- 4. Consider adding foreign key constraints in production
-- 5. Add appropriate indexes based on query patterns
-- 6. Implement table partitioning for performance_metrics in production
-- 7. Use database-specific optimizations (Oracle hints, PostgreSQL extensions)