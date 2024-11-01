#
# Also see:
# - https://spring.io/guides/topicals/spring-boot-docker/
# - https://hub.docker.com/_/eclipse-temurin
#
# Download all dependencies & cache them
#
FROM maven:3.9-eclipse-temurin-21 AS source
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
FROM openjdk:21-ea-jdk-bookworm AS carol-openapi-prod
# FROM eclipse-temurin:17-jre-alpine AS production
WORKDIR /opt/application/bin

#ARG user=carol
# Create a group and user named 'carol'
RUN groupadd -r carol && useradd -r -g carol carol

# Set the user to 'carol'
USER carol

#RUN useradd --create-home --no-log-init --shell /bin/bash ${user} \
#    && adduser ${user} sudo \
#	&& echo "${user}:1" | chpasswd
#RUN usermod -u 1000 ${user} && usermod -G 1000 ${user}	
#USER carol:carol

COPY --from=builder /opt/application/target/*.jar ./application.jar
CMD java -jar application.jar
