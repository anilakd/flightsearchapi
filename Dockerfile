FROM openjdk:17-jdk-slim
EXPOSE 8080
ARG JAR_FILE=target/flightsearchapi-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} flightsearchapi.jar
ENTRYPOINT ["java", "-jar","/flightsearchapi.jar"]