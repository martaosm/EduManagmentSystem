# Stage 1: Maven build
FROM maven:3.8.4-openjdk-17 as builder

# Copy the project files to the container
COPY ./EduManagmentSystem /app

# Set the working directory
WORKDIR /app

# Run maven clean install to build the application
RUN mvn clean install -DskipTests

# Stage 2: Create the final Docker image
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp
EXPOSE 8080
COPY --from=builder "/app/target/*.jar" account-app.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.7.0/opentelemetry-javaagent-all.jar /opentelemetry-javaagent-all.jar
ENV JAVA_TOOL_OPTIONS="-javaagent:/opentelemetry-javaagent-all.jar"
CMD [ "java", "-jar",  "/account-app.jar"]