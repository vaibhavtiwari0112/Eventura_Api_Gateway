# =============================
# Stage 1: Build
# =============================
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# =============================
# Stage 2: Run
# =============================
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar
COPY .env .env

# Expose port
EXPOSE 8080

# Build arguments (from CI/CD secrets)
ARG SERVER_PORT
ARG USER_SERVICE_URL
ARG CATALOG_SERVICE_URL
ARG SHOW_SERVICE_URL
ARG BOOKING_SERVICE_URL
ARG PAYMENT_SERVICE_URL
ARG NOTIFICATION_SERVICE_URL
ARG AUDIT_SERVICE_URL
ARG JWT_SECRET
ARG JWT_ISSUER
ARG JWT_EXPIRATION

# Convert build args to env vars
ENV SERVER_PORT=$SERVER_PORT
ENV USER_SERVICE_URL=$USER_SERVICE_URL
ENV CATALOG_SERVICE_URL=$CATALOG_SERVICE_URL
ENV SHOW_SERVICE_URL=$SHOW_SERVICE_URL
ENV BOOKING_SERVICE_URL=$BOOKING_SERVICE_URL
ENV PAYMENT_SERVICE_URL=$PAYMENT_SERVICE_URL
ENV NOTIFICATION_SERVICE_URL=$NOTIFICATION_SERVICE_URL
ENV AUDIT_SERVICE_URL=$AUDIT_SERVICE_URL
ENV JWT_SECRET=$JWT_SECRET
ENV JWT_ISSUER=$JWT_ISSUER
ENV JWT_EXPIRATION=$JWT_EXPIRATION

ENTRYPOINT ["java", "-jar", "app.jar"]
