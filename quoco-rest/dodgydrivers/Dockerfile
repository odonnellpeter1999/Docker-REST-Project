FROM openjdk:11.0.8-jre
EXPOSE 8082
VOLUME /tmp
COPY target/*.jar /dodgydrivers-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dodgydrivers-0.0.1-SNAPSHOT.jar"]

