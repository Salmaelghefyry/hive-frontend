#!/bin/bash

echo "Building all Hive microservices..."

# Build parent project
echo "Building parent project..."
mvn clean install -DskipTests

# Build each service
echo "Building Eureka Server..."
cd eureka-server && mvn clean package -DskipTests && cd ..

echo "Building API Gateway..."
cd api-gateway && mvn clean package -DskipTests && cd ..

echo "Building User Service..."
cd user-service && mvn clean package -DskipTests && cd ..

echo "Building Project Service..."
cd project-service && mvn clean package -DskipTests && cd ..

echo "Building Task Service..."
cd task-service && mvn clean package -DskipTests && cd ..

echo "Building Comment Service..."
cd comment-service && mvn clean package -DskipTests && cd ..

echo "All services built successfully!"
echo "You can now run: docker-compose up -d"
