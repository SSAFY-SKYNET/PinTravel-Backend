FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
ARG PROFILES
ARG ENV
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-Dspring.env=${ENV}", "-jar", "/app.jar"]