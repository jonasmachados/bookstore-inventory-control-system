# Build stage
FROM maven:3.8.1-openjdk-15 AS build
WORKDIR /projeto/backend
COPY . .
RUN mvn clean package -Pprod -DskipTests

# Package stage
FROM adoptopenjdk:15-jdk-hotspot
COPY --from=build /projeto/backend/target/backend-0.0.1-SNAPSHOT.jar backend.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend.jar"]
