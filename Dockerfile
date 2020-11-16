# syntax=docker/dockerfile:experimental
FROM maven:3.6.3-jdk-11 AS builder
COPY MyApp /MyApp
RUN --mount=type=cache,target=/root/.m2 \
    cd /MyApp && mvn --batch-mode clean package


FROM adoptopenjdk/openjdk11:jre-11.0.9_11-ubuntu 
COPY --from=builder /MyApp/target/MyApp-*-jar-with-dependencies.jar /MyApp.jar
CMD java -cp /MyApp.jar fr.univtln.bruno.samples.App
