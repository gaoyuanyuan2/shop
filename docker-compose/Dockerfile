FROM java:8
FROM maven:alpine
WORKDIR /app
EXPOSE 8080
LABEL maintainer="823789010@qq.com"
ADD ./shop-0.0.1-SNAPSHOT.jar shop-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","shop-0.0.1-SNAPSHOT.jar"]