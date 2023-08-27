FROM openjdk:17-jdk-slim
EXPOSE 8081
ARG JAR_FILE=target/flightsearchapi-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} flightsearchapi.jar
ENTRYPOINT ["java", "-jar","/flightsearchapi.jar"]