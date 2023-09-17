FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY target/invoice-0.0.1-SNAPSHOT.jar invoice.jar
ENTRYPOINT ["java","-jar","/invoice.jar"]