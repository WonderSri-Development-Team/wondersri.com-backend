FROM maven:2.7.14-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/demo-o.o.1-SNAPSHOT.jar demo.jar
EXPOSE=8081
ENTRYPOINT["java","-jar","demo.jar"]