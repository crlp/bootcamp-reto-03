FROM openjdk:11-jre
COPY target/ux-*SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]