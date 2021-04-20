FROM openjdk:8
COPY build/libs/testament-gateway-service-0.0.0.jar /app.jar
ENTRYPOINT java -jar /app.jar