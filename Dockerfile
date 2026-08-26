# Stage 1: Build application with Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the JAR file skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Run application with JDK runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]