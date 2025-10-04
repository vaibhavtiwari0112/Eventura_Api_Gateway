# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy everything needed for build
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN mvn -B -f pom.xml -ntp dependency:go-offline

# ✅ Copy source code including resources (like application.yml)
COPY src ./src

# ✅ Optional: copy .env or any extra config files if used
#COPY .env .env
COPY src/main/resources/application.yml src/main/resources/

RUN mvn -B clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# ---- Build args (passed from Render env variables) ----
ARG SERVER_PORT=8080
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

# ---- Set runtime environment variables ----
ENV SERVER_PORT=$SERVER_PORT \
    USER_SERVICE_URL=$USER_SERVICE_URL \
    CATALOG_SERVICE_URL=$CATALOG_SERVICE_URL \
    SHOW_SERVICE_URL=$SHOW_SERVICE_URL \
    BOOKING_SERVICE_URL=$BOOKING_SERVICE_URL \
    PAYMENT_SERVICE_URL=$PAYMENT_SERVICE_URL \
    NOTIFICATION_SERVICE_URL=$NOTIFICATION_SERVICE_URL \
    AUDIT_SERVICE_URL=$AUDIT_SERVICE_URL \
    JWT_SECRET=$JWT_SECRET \
    JWT_ISSUER=$JWT_ISSUER \
    JWT_EXPIRATION=$JWT_EXPIRATION

# ✅ Copy the JAR from build stage
COPY --from=build /workspace/target/*.jar app.jar

# ✅ Optional: expose actuator routes if needed
EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar","/app/app.jar"]
