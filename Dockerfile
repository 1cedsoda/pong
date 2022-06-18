FROM openjdk:18-slim-buster
COPY ./bin/winfpong-server.jar /home/winfpong-server.jar
EXPOSE 2347
EXPOSE 23347/udp
CMD ["java","-jar","/home/winfpong-server.jar"]