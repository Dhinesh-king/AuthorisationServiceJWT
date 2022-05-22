FROM openjdk:8
EXPOSE 8084
ADD target/AuthorizationServer.jar AuthorizationServer.jar
ENTRYPOINT ["java","-jar","/AuthorizationServer.jar"]