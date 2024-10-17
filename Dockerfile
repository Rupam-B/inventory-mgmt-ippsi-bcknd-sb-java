
#FROM maven:3.8.6-jdk-8 AS build

# Set the working directory in the container for building
#WORKDIR /app

# Copy the pom.xml and project files to the container
#COPY . .

# Build the project (this creates the JAR file in the target directory)
#RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot application
#FROM openjdk:8-jdk-alpine

# Set the working directory in the container for running
#WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
#COPY --from=build /target/Store-Management-System-0.0.1-SNAPSHOT.jar Store-Management-System.jar


# Expose the port the Spring Boot app runs on
#EXPOSE 8080

# Run the JAR file
#ENTRYPOINT ["java", "-jar", "Store-Management-System.jar"]

FROM openjdk:8-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
