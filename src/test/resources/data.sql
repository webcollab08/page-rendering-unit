-- Test data for Oracle database
INSERT INTO page_metadata (page_id, title, description, keywords, author, is_active) 
VALUES ('landing-home', 'Home Page', 'Welcome to our homepage', 'home,welcome,landing', 'Test Author', 1);

INSERT INTO branding_config (config_name, logo_url, primary_color, secondary_color, font_family, theme_name, is_active)
VALUES ('test-config', '/static/images/test-logo.png', '#007bff', '#6c757d', 'Arial, sans-serif', 'test', 1);

INSERT INTO performance_metrics (page_id, load_time_ms, resource_count, is_active)
VALUES ('landing-home', 150, 5, 1);

INSERT INTO responsive_breakpoints (device_type, min_width, max_width, is_active)
VALUES ('mobile', 0, 767, 1);