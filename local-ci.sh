#!/bin/bash

echo "🚀 Starting Local CI Pipeline..."

# Start Oracle database
echo "📦 Starting Oracle database..."
docker-compose up -d oracle-db

# Wait for Oracle to be ready
echo "⏳ Waiting for Oracle database..."
sleep 30

# Run tests
echo "🧪 Running tests..."
mvn clean test -Dspring.profiles.active=test

# Build application
echo "🔨 Building application..."
mvn clean package -DskipTests

# Build Docker image
echo "🐳 Building Docker image..."
docker build -t page-rendering-unit:local .

echo "✅ Local CI Pipeline completed!"

# Cleanup
echo "🧹 Cleaning up..."
docker-compose down