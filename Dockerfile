ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

COPY build/libs/CalorieTracker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]