-- Test data for Oracle database
-- Insert test page metadata
INSERT INTO page_metadata (id, page_id, title, description, keywords, author, is_active, created_date, last_modified) 
VALUES (1, 'landing-home', 'Home Page', 'Welcome to our homepage', 'home,welcome,landing', 'Test Author', 1, SYSDATE, SYSDATE);

-- Insert test branding config
INSERT INTO branding_config (id, config_name, logo_url, primary_color, secondary_color, font_family, theme_name, css_overrides, is_active, created_date, last_modified)
VALUES (1, 'test-config', '/static/images/test-logo.png', '#007bff', '#6c757d', 'Arial, sans-serif', 'test', null, 1, SYSDATE, SYSDATE);

-- Insert test performance metrics
INSERT INTO performance_metrics (id, page_id, avg_load_time, resource_count, optimization_score, is_active, created_date, last_modified)
VALUES (1, 'landing-home', 150, 5, 85, 1, SYSDATE, SYSDATE);

-- Insert test responsive breakpoints
INSERT INTO responsive_breakpoints (id, device_type, min_width, max_width, css_rules, is_active, created_date, last_modified)
VALUES (1, 'mobile', 0, 767, 'body { font-size: 14px; }', 1, SYSDATE, SYSDATE);