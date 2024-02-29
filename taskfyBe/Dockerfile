FROM maven:3.9.6-eclipse-temurin-17 as BUILDER

WORKDIR /app

COPY pom.xml .
COPY Dockerfile .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17.0.10_7-jre-jammy

WORKDIR /app

COPY --from=BUILDER /app/target/Taskfy.jar /app/Taskfy.jar

EXPOSE 8080

CMD ["java", "-jar", "Taskfy.jar"]
