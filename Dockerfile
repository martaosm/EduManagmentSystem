FROM maven:3.9-amazoncorretto-21 as before
WORKDIR /app
COPY /EduManagmentSystem/pom.xml .
RUN mvn dependency:resolve-plugins dependency:resolve
ADD EduManagmentSystem /app
ARG DB_HOST
ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD
ENV DB_HOST=$DB_HOST
ENV DB_NAME=$DB_NAME
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
RUN mvn install

FROM openjdk:17-oracle
COPY --from=before "/app/target/*.jar" study-plan-app.jar
EXPOSE 8081
CMD [ "java", "-jar",  "/study-plan-app.jar"]