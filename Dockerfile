# 1. Etapa de Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Compilamos y saltamos tests para que sea rápido
RUN mvn clean package -DskipTests

# 2. Etapa de Ejecución (Run)
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copiamos el JAR generado
COPY --from=build /app/target/*.jar app.jar

# --- LA CLAVE ---
# Forzamos a Spring Boot a usar el puerto 8080, ignorando el 8082 local
ENV SERVER_PORT=8080
# Le decimos a Railway que escuche en el 8080
EXPOSE 8080

# Arrancamos
ENTRYPOINT ["java", "-jar", "app.jar"]