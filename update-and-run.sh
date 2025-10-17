#!/bin/bash
# Stop running containers
docker stop $(docker ps -q) 2>/dev/null

# Remove old images
docker rmi $(docker images webc0llab/page-rendering-unit -q) --force 2>/dev/null

# Pull latest
docker pull webc0llab/page-rendering-unit:latest

# Run new container
docker run -p 8080:8080 webc0llab/page-rendering-unit:latest