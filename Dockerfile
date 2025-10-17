# Multi-stage build for Spring Boot application
FROM maven:3.9.4-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jre-slim

# Install Oracle Instant Client
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://download.oracle.com/otn_software/linux/instantclient/1923000/instantclient-basic-linux.x64-19.23.0.0.0dbru.zip && \
    unzip instantclient-basic-linux.x64-19.23.0.0.0dbru.zip && \
    mv instantclient_19_23 /opt/oracle && \
    rm instantclient-basic-linux.x64-19.23.0.0.0dbru.zip && \
    apt-get remove -y wget unzip && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

# Set Oracle environment variables
ENV LD_LIBRARY_PATH=/opt/oracle:$LD_LIBRARY_PATH
ENV ORACLE_HOME=/opt/oracle

# Create app directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/page-rendering-unit-*.jar app.jar

# Create non-root user
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown -R appuser:appuser /app
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/page-rendering/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]