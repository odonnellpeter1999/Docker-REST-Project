FROM openjdk:11.0.8-jre
ENV HOST=localhost
COPY target/*-jar-with-dependencies.jar /client-0.0.1-SNAPSHOT.jar
CMD sleep 10 && java -jar /client-0.0.1-SNAPSHOT.jar $HOST
