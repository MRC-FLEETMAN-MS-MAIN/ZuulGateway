FROM openjdk:8-jdk-alpine
COPY PhotoAppApiZuulAPIGateway-0.0.1-SNAPSHOT.jar ZuulAPIGateway.jar
ENTRYPOINT ["java","-jar","ZuulAPIGateway.jar"]
