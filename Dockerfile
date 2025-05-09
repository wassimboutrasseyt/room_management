# Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight JDK to run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/room_management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]




























## Use an official Maven image with JDK 17 for the build stage
#FROM maven:3.9.6-eclipse-temurin-17 AS builder
#
## Set working directory
#WORKDIR /app
#
## Copy pom.xml and download dependencies first (better caching)
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
## Copy the rest of the source code
#COPY src/main/java/com/example/room_management .
#
## Build the application
#RUN mvn clean package -DskipTests
#
## ----------------------
#
## Use a smaller JDK image for the runtime
#FROM eclipse-temurin:17-jdk-alpine
#
## Set working directory
#WORKDIR /app
#
## Copy the built jar file from the builder stage
#COPY --from=builder /app/target/room_management-0.0.1-SNAPSHOT.jar app.jar
#
## Expose the application port
#EXPOSE 8082
#
## Run the jar
#ENTRYPOINT ["java", "-jar", "app.jar"]
