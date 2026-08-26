# Stage 1: Build the Maven application
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy all repository files into the container
COPY . .

# Find pom.xml and build the project regardless of subfolder structure
RUN if [ -f "pom.xml" ]; then \
        mvn clean package -DskipTests; \
    elif [ -f "backend/pom.xml" ]; then \
        cd backend && mvn clean package -DskipTests; \
    else \
        echo "No pom.xml found!"; exit 1; \
    fi

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/**/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]