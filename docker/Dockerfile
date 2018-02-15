FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./cdr-forwarder.jar /app
COPY ./config/application.properties /app/config/
#EXPOSE 8887
CMD ["java","-jar","cdr-forwarder.jar"]