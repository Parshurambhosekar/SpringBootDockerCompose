
FROM openjdk:11

COPY target/docker_compose_deployement_app.jar /usr/app/

WORKDIR /usr/app/

EXPOSE 8085

ENTRYPOINT [ "java","-jar","docker_compose_deployement_app.jar" ]