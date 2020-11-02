FROM openjdk:11.0.8-jre
EXPOSE 8083
VOLUME /tmp
COPY target/*.jar /girlpower-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/girlpower-0.0.1-SNAPSHOT.jar"]

