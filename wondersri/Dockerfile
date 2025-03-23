# Build stage
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim-buster
WORKDIR /app
COPY --from=build /app/target/wondersri-0.0.1-SNAPSHOT.jar wondersri.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "wondersri.jar"]