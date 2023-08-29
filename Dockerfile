#FROM openjdk:17-jdk-slim
#EXPOSE 8081
#ARG JAR_FILE=target/flightsearchapi-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} flightsearchapi.jar
#ENTRYPOINT ["java", "-jar","/flightsearchapi.jar"]

# select parent image
FROM maven:3.8.3-openjdk-17-slim
# copy the source tree and the pom.xml to our new container
COPY ./ ./
# package our application code
RUN mvn clean package
# set the startup command to execute the jar
CMD ["java", "-jar", "target/flightsearchapi-0.0.1-SNAPSHOT.jar"]