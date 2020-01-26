# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11:latest

# Add Maintainer Info
LABEL maintainer="ivolopes.o@outlook.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9073 available to the world outside this container
EXPOSE 9073

# The application's jar file
ARG JAR_FILE=target/admin-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} admin-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin-service.jar"]
