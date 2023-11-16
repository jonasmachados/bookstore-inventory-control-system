# Build stage
FROM maven AS build
COPY ./projeto/backend /app
WORKDIR /app
RUN mvn clean package -Pprod -DskipTests

# Package stage
FROM adoptopenjdk:15-jre-hotspot
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /backend.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","/backend.jar"]