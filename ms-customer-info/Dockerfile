# Etapa de compilación
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Etapa de despliegue
FROM openjdk:17-jdk-slim AS deployment
WORKDIR /app
COPY --from=build /app/target/ms-customer-info-*.jar ms-customer-info.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-jar", "ms-customer-info.jar"]