FROM openjdk:12
VOLUME /temp
EXPOSE 8082
ADD ./target/microservicio-usuario-0.0.1-SNAPSHOT.jar usuario.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=America/Bogota",  "/usuario.jar"]