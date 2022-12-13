FROM amazoncorretto:11-alpine3.16-jdk
EXPOSE 8080
ADD target/blockbuster.jar blockbuster.jar
ENTRYPOINT ["java","-jar","blockbuster.jar"]