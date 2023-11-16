FROM openjdk:17-slim as build
COPY target/rental-service-0.0.1-SNAPSHOT.jar rental-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "rental-service-0.0.1-SNAPSHOT.jar"]