FROM maven:latest AS build
WORKDIR /Meteo-SCD
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /Meteo-SCD
COPY --from=build /Meteo-SCD/target/scd-0.0.1-SNAPSHOT.jar scd.jar
ENTRYPOINT ["java", "-jar", "/Meteo-SCD/scd.jar"]
