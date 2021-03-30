FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/wx-java-miniapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]