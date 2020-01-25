# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11:latest

# Add Maintainer Info
LABEL maintainer="ivolopes.o@outlook.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9083 available to the world outside this container
EXPOSE 9083

# The application's jar file
ARG JAR_FILE=target/music-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} music-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/music-service.jar"]
