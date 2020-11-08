FROM java:8
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /waiter-service.jar
ENTRYPOINT ["java","-jar","shop-0.0.1-SNAPSHOT.jar"]