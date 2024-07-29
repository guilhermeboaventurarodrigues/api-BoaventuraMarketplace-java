# Use uma imagem base do Maven com JDK 17 para compilar a aplicação
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use uma imagem base do OpenJDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Copie o arquivo .env para o container
COPY .env .env

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]