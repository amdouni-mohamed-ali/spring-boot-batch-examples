# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

# copy application JAR (with libraries inside)
COPY ./target/multiple-datasources-example.jar /app.jar

# export the spring boot default port
EXPOSE 8080

# specify default command
CMD ["/usr/bin/java", "-jar", "/app.jar"]