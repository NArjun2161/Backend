FROM maven:3.6-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn dependency:go-offline -DskipTests
RUN mvn package
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]
