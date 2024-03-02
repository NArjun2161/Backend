# Use a multi-stage build for efficiency
FROM maven:3.8-openjdk-17 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src src

# Install dependencies (skip tests to optimize build size)
RUN mvn dependency:go-offline -DskipTests

# Build the application and copy the JAR file
RUN mvn package

# Switch to a lightweight Alpine-based image for runtime
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port used by your Spring Boot application (adjust if needed)
EXPOSE 8083

# Command to run the application
CMD ["java", "-jar", "app.jar"]
