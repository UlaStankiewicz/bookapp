FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/book-0.0.1-SNAPSHOT.jar bookapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bookapp.jar"]