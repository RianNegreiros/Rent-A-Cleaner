FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/ExpressCleaning-0.0.1-SNAPSHOT.jar /app/app.jar

ENV SPRING_PROFILE=dckr

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]