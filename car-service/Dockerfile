FROM openjdk:17-slim as build
COPY target/car-service-0.0.1-SNAPSHOT.jar car-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "car-service-0.0.1-SNAPSHOT.jar"]