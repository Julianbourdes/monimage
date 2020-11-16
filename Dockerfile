FROM maven:3.6.3-jdk-11 AS builder
COPY MyApp /MyApp
RUN cd /MyApp && mvn package

FROM adoptopenjdk/openjdk11:jre-11.0.9_11-ubuntu 
COPY --from=builder /MyApp/target/MyApp-1.0-SNAPSHOT.jar /MyApp.jar
CMD java -cp /MyApp.jar fr.univtln.bruno.samples.App
