FROM amazoncorretto:21-alpine

WORKDIR /app

COPY target/eureka-server.jar eureka-server.jar

EXPOSE 8761

CMD ["java", "-jar", "eureka-server.jar"]