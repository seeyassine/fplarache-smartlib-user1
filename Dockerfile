#FROM openjdk:17-oracle
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar", "app.jar"]

# Stage 1: Build the application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Package the application with OpenJDK 17
FROM openjdk:17-oracle
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]

