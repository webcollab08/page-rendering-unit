#!/bin/bash
# Stop and remove page-rendering-unit container
docker stop page-rendering-unit 2>/dev/null
docker rm page-rendering-unit 2>/dev/null

# Pull latest image
docker pull webc0llab/page-rendering-unit:latest

# Start containers using docker-compose
docker compose up -d

echo "Application started at http://localhost:8080/page-rendering"