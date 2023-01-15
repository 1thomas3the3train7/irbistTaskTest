FROM openjdk:17
ADD /target/irbisBlock-0.0.1-SNAPSHOT.jar irbis.jar
ENTRYPOINT ["java","-jar","irbis.jar"]