package com.tiucd.portfolio.pagerendering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Page Rendering Unit - Spring Boot Application
 * Feature-Based Architecture with Service-Oriented patterns
 * 
 * PRODUCTION MIGRATION NOTES:
 * - Add application monitoring with Micrometer/Actuator
 * - Configure logging with Logback for production
 * - Add security configuration with Spring Security
 * - Implement health checks and readiness probes for Kubernetes
 * - Add distributed tracing with Zipkin/Jaeger
 */
@SpringBootApplication
@EnableJpaRepositories
public class PageRenderingApplication {

    public static void main(String[] args) {
        // PRODUCTION MIGRATION: Add JVM tuning parameters
        // -Xms512m -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200
        
        SpringApplication.run(PageRenderingApplication.class, args);
        
        // PRODUCTION MIGRATION: Add startup logging and health check
        System.out.println("=================================================");
        System.out.println("Page Rendering Unit Started Successfully");
        System.out.println("Access the application at: http://localhost:8080/page-rendering");
        System.out.println("H2 Console (Development): http://localhost:8080/page-rendering/h2-console");
        System.out.println("API Documentation: http://localhost:8080/page-rendering/swagger-ui.html");
        System.out.println("=================================================");
    }
}