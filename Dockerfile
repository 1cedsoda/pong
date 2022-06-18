FROM openjdk:18-slim-buster
COPY ./bin/winfpong-server.jar /home/winfpong-server.jar
CMD ["java","-jar","/home/winfpong-server.jar"]