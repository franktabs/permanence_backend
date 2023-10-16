FROM amazoncorretto:17.0.7-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY target/bootstrap.properties bootstrap.properties

ENTRYPOINT ["java","-jar","-Xmx256m","/app.jar"]