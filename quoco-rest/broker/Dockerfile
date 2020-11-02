FROM openjdk:11.0.8-jre
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar /broker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/broker-0.0.1-SNAPSHOT.jar"]

