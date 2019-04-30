FROM openjdk:8u181-jre

ENV APP_VERSION 1.0.0-SNAPSHOT
ENV DOCKERIZE_VERSION v0.6.1

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app

COPY itoken-eureka-$APP_VERSION.jar /app/app.jar

ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://192.168.198.136:8888/eureka/prod/master", "java", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8761