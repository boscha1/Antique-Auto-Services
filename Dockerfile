FROM openjdk:11
ADD target/antique-auto-services.jar antique-auto-services.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "antique-auto-services.jar"]