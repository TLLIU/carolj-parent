#
# Also see:
# - https://spring.io/guides/topicals/spring-boot-docker/
# - https://hub.docker.com/_/eclipse-temurin
#
# Download all dependencies & cache them
#
FROM maven:3.9.2-eclipse-temurin-17 AS source
ENV APPLICATION_HOME=/opt/application/
WORKDIR "${APPLICATION_HOME}"
COPY pom.xml ./pom.xml
RUN mvn dependency:resolve-plugins dependency:go-offline
#
# Build & Package
#
FROM source AS builder
WORKDIR "${APPLICATION_HOME}"
COPY src src
RUN mvn clean package spring-boot:repackage
#
# Install
#
FROM eclipse-temurin:17-jre-alpine AS production
WORKDIR /opt/application/bin
RUN addgroup -S "service-account-group" && \
    adduser --system --no-create-home "service-account" -G "service-account-group"
USER service-account
COPY --from=builder /opt/application/target/*.jar ./application.jar
CMD java -jar application.jar
