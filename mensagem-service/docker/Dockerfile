FROM amazoncorretto:21
LABEL author="jessicagontijo"
WORKDIR /app
COPY mensagem-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]