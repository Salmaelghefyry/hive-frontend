#!/bin/bash

echo "Starting Hive microservices..."

# Start infrastructure services first
echo "Starting infrastructure services..."
docker-compose up -d postgres redis eureka-server

# Wait for Eureka to be ready
echo "Waiting for Eureka Server to be ready..."
sleep 30

# Start API Gateway
echo "Starting API Gateway..."
docker-compose up -d api-gateway

# Wait for Gateway to be ready
sleep 15

# Start business services
echo "Starting business services..."
docker-compose up -d user-service project-service task-service comment-service

echo "All services started!"
echo "API Gateway available at: http://localhost:9999"
echo "Eureka Dashboard available at: http://localhost:8761"
