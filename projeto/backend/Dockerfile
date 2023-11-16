# Build stage
FROM maven AS build
COPY ./projeto/backend /projeto/backend
WORKDIR /projeto/backend
RUN mvn clean package -Pprod -DskipTests

# Package stage
FROM adoptopenjdk:15-jre-hotspot
COPY --from=build /projeto/backend/target/backend-0.0.1-SNAPSHOT.jar /backend.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/backend.jar"]