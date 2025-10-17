# Page Rendering Unit

A Spring Boot application for rendering dynamic web pages with Oracle database integration.

## Prerequisites

- Docker and Docker Compose
- Oracle Database (running locally on port 1521)
- Java 17+ (for local development)
- Maven 3.6+ (for local development)

## Quick Start with Docker

1. **Start Oracle Database** (if not already running):
   ```bash
   docker run -d --name oracle-xe -p 1521:1521 -e ORACLE_PASSWORD=oracle123 gvenzl/oracle-xe:21-slim
   ```

2. **Build and Run Application**:
   ```bash
   docker-compose up --build
   ```

3. **Access Application**:
   - Application: http://localhost:8080/page-rendering
   - Health Check: http://localhost:8080/page-rendering/actuator/health

## Database Configuration

The application connects to Oracle Database with these settings:
- **URL**: `jdbc:oracle:thin:@localhost:1521:XE`
- **Username**: `system`
- **Password**: `oracle123`

## Docker Configuration

The application uses:
- **Multi-stage build** for optimized image size
- **Oracle Instant Client** for database connectivity
- **Health checks** for container monitoring
- **Non-root user** for security

## Development

### Local Development
```bash
# Start Oracle database
docker run -d --name oracle-xe -p 1521:1521 -e ORACLE_PASSWORD=oracle123 gvenzl/oracle-xe:21-slim

# Run application
mvn spring-boot:run
```

### Testing
```bash
# Run unit tests (no database required)
mvn test
```

## API Endpoints

- `GET /page-rendering/api/pages/{pageId}` - Render a specific page
- `GET /page-rendering/api/branding/config` - Get branding configuration
- `GET /page-rendering/actuator/health` - Health check endpoint

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | Oracle database URL | `jdbc:oracle:thin:@localhost:1521:XE` |
| `SPRING_DATASOURCE_USERNAME` | Database username | `system` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `oracle123` |
| `SERVER_PORT` | Application port | `8080` |

## Troubleshooting

### Database Connection Issues
1. Ensure Oracle database is running: `docker ps | grep oracle`
2. Check database logs: `docker logs oracle-xe`
3. Verify connection: `sqlplus system/oracle123@localhost:1521/XE`

### Application Issues
1. Check application logs: `docker logs page-rendering-unit`
2. Verify health endpoint: `curl http://localhost:8080/page-rendering/actuator/health`
3. Check database connectivity from application container

## Production Notes

- CI/CD pipeline is currently disabled for local development
- Enable pipeline in `.github/workflows/ci-cd.yml` for production deployment
- Configure proper secrets for Docker Hub deployment
- Add monitoring and logging for production environment