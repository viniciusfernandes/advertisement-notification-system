FROM eclipse-temurin:21-jre
LABEL verion="1.0.0"
COPY ./build/libs/advertisement-notification-system-0.0.1-SNAPSHOT.jar advertisement-notification-system-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","advertisement-notification-system-0.0.1-SNAPSHOT.jar"]