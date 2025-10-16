# Page Rendering Unit - CI/CD Ready ✅ - Feature-Based Architecture Demo

## Overview
This is a Spring Boot implementation of the Page Rendering Unit following Feature-Based Architecture with Service-Oriented patterns. The application demonstrates modern software architecture principles with H2 in-memory database for development and comprehensive production migration guidance.

## Technology Stack
- **Java 17** - Latest LTS version
- **Spring Boot 3.2.0** - Latest Spring Boot version
- **H2 Database** - In-memory database for development
- **Thymeleaf** - Template engine for web UI
- **Bootstrap 5.3.2** - CSS framework
- **jQuery 3.7.1** - JavaScript library
- **Maven** - Build tool

## Features
- ✅ Page rendering with dynamic branding
- ✅ Performance monitoring and analytics
- ✅ Responsive web design
- ✅ RESTful API endpoints
- ✅ Web UI with Thymeleaf templates
- ✅ H2 database with sample data
- ✅ Production migration documentation

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Application
```bash
# Clone and navigate to the project
cd page_rendering_unit

# Run the application
mvn spring-boot:run

# Or build and run the JAR
mvn clean package
java -jar target/page-rendering-unit-1.0.0.jar
```

### Access Points
- **Landing Page**: http://localhost:8080/page-rendering/api/page/view/landing-home
- **Dashboard**: http://localhost:8080/page-rendering/api/page/dashboard
- **H2 Console**: http://localhost:8080/page-rendering/h2-console
- **Health Check**: http://localhost:8080/page-rendering/actuator/health

### H2 Database Connection
- **URL**: `jdbc:h2:mem:pagedb`
- **Username**: `sa`
- **Password**: (empty)

## API Endpoints

### REST API
- `GET /api/page/render/{pageId}` - Render page (JSON response)
- `GET /api/page/metadata/{pageId}` - Get page metadata
- `GET /api/page/performance/{pageId}` - Get performance metrics
- `POST /api/page/branding/activate/{configName}` - Activate branding

### Web UI
- `GET /api/page/view/{pageId}` - View page (HTML)
- `GET /api/page/dashboard` - Management dashboard
- `GET /api/page/analytics/{pageId}` - Performance analytics

## Architecture

### Package Structure
```
com.tiucd.portfolio.pagerendering/
├── controller/          # REST controllers and web endpoints
├── service/            # Business logic services
├── repository/         # Data access layer (JDBC)
├── model/
│   ├── entity/        # Database entities
│   └── dto/           # Data transfer objects
├── config/            # Configuration classes
└── integration/       # External service clients
```

### Key Components
- **PageRenderingService** - Core page rendering logic
- **BrandingService** - Branding configuration management
- **PerformanceOptimizationService** - Performance monitoring
- **PageMetadataRepository** - Page metadata data access
- **BrandingConfigRepository** - Branding configuration data access

## Testing
```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report
```

## Production Migration Guide

### Database Migration
The application uses H2 in-memory database for development. For production deployment:

#### Oracle Database
```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@//localhost:1521/AWSDB
    driver-class-name: oracle.jdbc.OracleDriver
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

**Dependencies to add:**
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
</dependency>
```

#### PostgreSQL Database
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/awsdb
    driver-class-name: org.postgresql.Driver
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

**Dependencies to add:**
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

#### MySQL Database
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/awsdb
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

**Dependencies to add:**
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

### Configuration Changes for Production
1. **Remove H2 console configuration**
2. **Set Thymeleaf cache to true**
3. **Adjust log levels to INFO/WARN**
4. **Configure connection pooling**
5. **Add security configurations**
6. **Implement proper error handling**
7. **Add monitoring and alerting**

### Performance Optimizations
- Implement Redis caching for branding configurations
- Add CDN integration for static assets
- Configure database connection pooling
- Implement page pre-rendering
- Add performance monitoring (APM tools)

## Development Notes
- All configuration files include detailed production migration comments
- Database schema supports multiple database types with migration notes
- Service classes include caching and optimization guidance
- Repository classes use JDBC with production migration patterns

## Support
For questions or issues, refer to the inline documentation and production migration comments throughout the codebase.