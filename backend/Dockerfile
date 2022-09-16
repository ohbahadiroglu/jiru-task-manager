FROM amazoncorretto:18.0.2-alpine
MAINTAINER jiruTeam
COPY out/jiru-app.jar docker-jiru-app.jar
ENTRYPOINT ["java","-jar","/docker-jiru-app.jar"]
EXPOSE 8081
