FROM maven:3.9.3-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/TestYouAi-0.1.0.jar app.jar

COPY src/main/resources/app.pub /app/app.pub
COPY src/main/resources/app.key /app/app.key

COPY src/main/resources/application.yml /app/application.yml

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/testyouai_db
ENV SPRING_DATASOURCE_USERNAME=testyouai_user
ENV SPRING_DATASOURCE_PASSWORD=testyouai_pass
ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME=org.postgresql.Driver

ENV OPENAI_API_KEY=${OPENAI_API_KEY}

ENTRYPOINT ["java","-jar","app.jar"]
