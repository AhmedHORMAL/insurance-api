# Étape 1 : builder avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests -pl bootstrap -am

# Étape 2 : image minimale avec JAR
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/bootstrap/target/*.jar app.jar


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

