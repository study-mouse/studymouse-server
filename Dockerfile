FROM openjdk:8-jdk-alpine

ADD ./build/libs/*.jar studymouse-server.jar
ENTRYPOINT ["java", "-jar", "/studymouse-server.jar"]