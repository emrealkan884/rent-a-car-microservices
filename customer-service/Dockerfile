FROM openjdk:17-slim as build
COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "customer-service-0.0.1-SNAPSHOT.jar"]