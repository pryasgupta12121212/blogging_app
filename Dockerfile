FROM openjdk:17-jdk

COPY target/crudapp.jar .
EXPOSE 8080

ENTRYPOINT ["java","-jar","crudapp.jar"]