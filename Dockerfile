# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="mathieu@ronvel.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/farztev-admin-backend-0.0.1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} farztev-admin-backend.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","$params","/farztev-admin-backend.jar"]