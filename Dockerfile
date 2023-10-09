FROM openjdk:17

WORKDIR  /app

COPY target/hub_payments-0.0.1-SNAPSHOT.jar /app/hub_payments-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/hub_payments-0.0.1-SNAPSHOT.jar"]