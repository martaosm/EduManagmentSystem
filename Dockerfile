FROM maven:3.9-amazoncorretto-21 as before
WORKDIR /app
COPY /EduManagmentSystem/pom.xml .
RUN mvn dependency:resolve-plugins dependency:resolve
ADD EduManagmentSystem /app
RUN mvn install

FROM openjdk:17-oracle
COPY --from=before "/app/target/*.jar" study-plan-app.jar
EXPOSE 8080
CMD [ "java", "-jar",  "/study-plan-app.jar"]