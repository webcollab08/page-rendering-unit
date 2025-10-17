-- Sample Data for Oracle Database

-- Sample Page Metadata
INSERT INTO page_metadata (page_id, title, description, keywords, author, is_active) VALUES
('landing-home', 'Sample Portfolio', 'Welcome to TIUCD Portfolio showcasing innovative solutions', 'portfolio, technology, innovation, solutions', 'TIUCD Team', 1);

INSERT INTO page_metadata (page_id, title, description, keywords, author, is_active) VALUES
('about-us', 'About Us - TIUCD Portfolio', 'Learn about our company mission and values', 'about, company, mission, values, team', 'TIUCD Team', 1);

INSERT INTO page_metadata (page_id, title, description, keywords, author, is_active) VALUES
('services', 'Our Services - TIUCD Portfolio', 'Comprehensive technology services and solutions', 'services, technology, consulting, development', 'TIUCD Team', 1);

INSERT INTO page_metadata (page_id, title, description, keywords, author, is_active) VALUES
('contact', 'Contact Us - TIUCD Portfolio', 'Get in touch with our expert team', 'contact, support, consultation, inquiry', 'TIUCD Team', 1);

-- Sample Branding Configuration
INSERT INTO branding_config (config_name, logo_url, primary_color, secondary_color, font_family, theme_name, css_overrides, is_active) VALUES
('default-theme', '/static/images/logo.png', '#007bff', '#6c757d', 'Arial, sans-serif', 'modern', '.navbar { box-shadow: 0 2px 4px rgba(0,0,0,0.1); }', 1);

INSERT INTO branding_config (config_name, logo_url, primary_color, secondary_color, font_family, theme_name, css_overrides, is_active) VALUES
('dark-theme', '/static/images/logo-dark.png', '#343a40', '#495057', 'Roboto, sans-serif', 'dark', '.body { background-color: #212529; color: #ffffff; }', 0);

INSERT INTO branding_config (config_name, logo_url, primary_color, secondary_color, font_family, theme_name, css_overrides, is_active) VALUES
('corporate-theme', '/static/images/logo-corp.png', '#004085', '#0056b3', 'Georgia, serif', 'corporate', '.header { border-bottom: 3px solid #004085; }', 0);

-- Sample Performance Metrics
INSERT INTO performance_metrics (page_id, load_time_ms, render_time_ms, resource_count, page_size_kb, user_agent, device_type) VALUES
('landing-home', 1250, 850, 15, 245, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'desktop');

INSERT INTO performance_metrics (page_id, load_time_ms, render_time_ms, resource_count, page_size_kb, user_agent, device_type) VALUES
('landing-home', 1850, 1200, 15, 245, 'Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X)', 'mobile');

INSERT INTO performance_metrics (page_id, load_time_ms, render_time_ms, resource_count, page_size_kb, user_agent, device_type) VALUES
('about-us', 980, 650, 12, 180, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'desktop');

INSERT INTO performance_metrics (page_id, load_time_ms, render_time_ms, resource_count, page_size_kb, user_agent, device_type) VALUES
('services', 1150, 750, 18, 320, 'Mozilla/5.0 (iPad; CPU OS 14_0 like Mac OS X)', 'tablet');

INSERT INTO performance_metrics (page_id, load_time_ms, render_time_ms, resource_count, page_size_kb, user_agent, device_type) VALUES
('contact', 890, 580, 10, 150, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'desktop');

-- Sample Responsive Breakpoints
INSERT INTO responsive_breakpoints (device_type, min_width, max_width, grid_columns, is_active) VALUES
('mobile', 0, 767, 12, 1);

INSERT INTO responsive_breakpoints (device_type, min_width, max_width, grid_columns, is_active) VALUES
('tablet', 768, 1023, 12, 1);

INSERT INTO responsive_breakpoints (device_type, min_width, max_width, grid_columns, is_active) VALUES
('desktop', 1024, 1919, 12, 1);

INSERT INTO responsive_breakpoints (device_type, min_width, max_width, grid_columns, is_active) VALUES
('large-desktop', 1920, NULL, 12, 1);

COMMIT;