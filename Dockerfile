# Stage 1: Build the Spring Boot application
FROM maven:3.8.6-jdk-8 AS build

# Set the working directory in the container for building
WORKDIR /app

# Copy the pom.xml and project files to the container
COPY . .

# Build the project (this creates the JAR file in the target directory)
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot application
FROM openjdk:8-jdk-alpine

# Set the working directory in the container for running
#WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/Store-Management-System-0.0.1-SNAPSHOT.jar Store-Management-System.jar

# Expose the port the Spring Boot app runs on
EXPOSE 8080

# Set JVM options (garbage collector settings and memory limits)
ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Xmx256m -Xms128m"

# Run the JAR file with JVM options
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar Store-Management-System.jar"]
